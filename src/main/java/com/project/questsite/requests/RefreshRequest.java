package com.project.questsite.requests;

import lombok.Data;

@Data
public class RefreshRequest {

	Long userId;
	String refreshToken;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
