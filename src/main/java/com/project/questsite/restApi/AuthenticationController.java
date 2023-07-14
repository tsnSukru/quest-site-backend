package com.project.questsite.restApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questsite.bussines.IUserService;
import com.project.questsite.bussines.RefreshTokenManager;
import com.project.questsite.entities.RefreshToken;
import com.project.questsite.entities.User;
import com.project.questsite.requests.RefreshRequest;
import com.project.questsite.requests.UserRequest;
import com.project.questsite.responses.AuthenticationResponse;
import com.project.questsite.security.JwtTokenProvider;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	private AuthenticationManager authenticationManager;

	private JwtTokenProvider jwtTokenProvider;

	private IUserService userManager;

	private PasswordEncoder passwordEncoder;

	private RefreshTokenManager refreshTokenService;

	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, IUserService userManager,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
			RefreshTokenManager refreshTokenService) {
		this.authenticationManager = authenticationManager;
		this.userManager = userManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.refreshTokenService = refreshTokenService;
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		User user = userManager.getByUserName(loginRequest.getUsername());
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return authResponse;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest registerRequest) {
		AuthenticationResponse authResponse = new AuthenticationResponse();
		if (userManager.getByUserName(registerRequest.getUsername()) != null) {
			authResponse.setMessage("Username already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userManager.add(user);
		user = userManager.getByUserName(registerRequest.getUsername());

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				registerRequest.getUsername(), registerRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);

		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthenticationResponse response = new AuthenticationResponse();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if (token.getToken().equals(refreshRequest.getRefreshToken()) && !refreshTokenService.isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage("token successfully refreshed.");
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setMessage("refresh token is not valid.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}

	}
}
