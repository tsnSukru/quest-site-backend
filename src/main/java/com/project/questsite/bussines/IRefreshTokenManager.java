package com.project.questsite.bussines;

import com.project.questsite.entities.RefreshToken;
import com.project.questsite.entities.User;

public interface IRefreshTokenManager {
	public String createRefreshToken(User user);

	public boolean isRefreshExpired(RefreshToken token);

	public RefreshToken getByUser(Long userId);
}
