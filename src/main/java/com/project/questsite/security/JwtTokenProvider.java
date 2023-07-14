package com.project.questsite.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${questsite.app.secret}")
	private String APP_SECRET;

	@Value("${questsite.expires.in}")
	private Long EXPIRES_IN;

	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userDetails.getId())).setIssuedAt(new Date())
				.setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}

	public String generateJwtTokenByUserId(Long userId) {
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userId)).setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}

	public Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		String subject = claims.getSubject();

		if (subject != null) {
			long userId = Long.parseLong(subject);
			return userId;
		} else {
			throw new IllegalStateException("Geçersiz JWT talep konusu");
		}
	}

	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
			// TODO: handle exception
			return false;
		} catch (MalformedJwtException e) {
			// TODO: handle exception
			return false;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (UnsupportedJwtException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}
