package com.project.questsite.bussines;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.questsite.dataAccess.RefreshTokenRepository;
import com.project.questsite.entities.RefreshToken;
import com.project.questsite.entities.User;

@Service
public class RefreshTokenManager implements IRefreshTokenManager {
	@Value("${refresh.token.expires.in}")
	Long expireSeconds;

	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	public RefreshTokenManager(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if (token == null) {
			token = new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}

	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserId(userId);
	}
}
