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

import com.project.questsite.bussines.IRefreshTokenService;
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

	private IUserService userService;

	private PasswordEncoder passwordEncoder;

	private IRefreshTokenService refreshTokenService;

	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, IUserService userService,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
			RefreshTokenManager refreshTokenService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.refreshTokenService = refreshTokenService;
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody UserRequest loginRequest) {
		// Kimlik doğrulama işlemi için kullanılacak olan nesne
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword());

		// Kullanıcının kimlik bilgileri doğrumu diye kontrol edilir.
		// Eğer doğruysa,AuthenticationManager kimlik doğrulama nesnesi döndürür
		Authentication auth = authenticationManager.authenticate(authToken);

		// Kimlik bilgisi, SecurityContextHolder üzerinden güvenlik bağlamına atanır.
		// Bu kullanıcının oturum açmış olduğunu belirtir
		SecurityContextHolder.getContext().setAuthentication(auth);

		// Gelecekteki isteklerde kullanıcının kimlik bilgileri ile,
		// kimlik doğrulaması için kullanılacak bir token oluşturulur.
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		User user = userService.getByUserName(loginRequest.getUsername());
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		authResponse.setMessage("Success!");
		return authResponse;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest registerRequest) {
		// Front end'e gönderilecek olan nesne
		AuthenticationResponse authResponse = new AuthenticationResponse();
		// kullanıcı adı önceden alınmış mı?
		if (userService.getByUserName(registerRequest.getUsername()) != null) {
			authResponse.setMessage("Username already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}

		// kullanıcıyı veritabanına kaydetme
		User user = new User();
		user.setUserName(registerRequest.getUsername());

		// kullanıcı şifresinin gücenli bir şekilde depolanması için şifreleme işlemi
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userService.add(user);

		// kullanıcının otomatik atanan Id'sini almak için veritabanından çekme işlemi
		user = userService.getByUserName(registerRequest.getUsername());

		//// kimlik doğrulama işlemi için kullanıcı adı ve şifre ile nesne oluşturulur
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				registerRequest.getUsername(), registerRequest.getPassword());

		// authenticationManager nesnesi ile kimlik doğrulama işlemi yapılır.
		// Eğer kimlik doğrulama işlemi başarılıysa bir kimlik doğrulama nesnesi
		// döndürür.
		Authentication auth = authenticationManager.authenticate(authToken);

		// Kimlik bilgisi, SecurityContextHolder üzerinden güvenlik bağlamına atanır.
		// Bu kullanıcının oturum açmış olduğunu belirtir
		SecurityContextHolder.getContext().setAuthentication(auth);

		// Gelecekteki isteklerde kullanıcının kimlik bilgileri ile,
		// kimlik doğrulaması için kullanılacak bir token oluşturulur.
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);

		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	// token'ın süresi geçtiyse yenisini oluştur
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
