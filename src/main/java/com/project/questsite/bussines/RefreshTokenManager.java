package com.project.questsite.bussines;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.questsite.dataAccess.RefreshTokenDal;
import com.project.questsite.entities.RefreshToken;
import com.project.questsite.entities.User;

@Service
public class RefreshTokenManager implements IRefreshTokenService {
	@Value("${refresh.token.expires.in}")
	Long expireSeconds;

	private RefreshTokenDal refreshTokenDal;

	@Autowired
	public RefreshTokenManager(RefreshTokenDal refreshTokenDal) {
		this.refreshTokenDal = refreshTokenDal;
	}

	@Override
	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenDal.findByUserId(user.getId());
		if (token == null) {
			token = new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenDal.save(token);
		return token.getToken();
	}

	@Override
	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	@Override
	public RefreshToken getByUser(Long userId) {
		return refreshTokenDal.findByUserId(userId);
	}
}
