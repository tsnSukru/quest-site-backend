package com.project.questsite.responses;

import com.project.questsite.entities.PostLike;

import lombok.Data;

@Data
public class PostLikeResponse {
	Long id;
	Long userId;
	Long postId;

	public PostLikeResponse(PostLike postLike) {
		// TODO Auto-generated constructor stub
		this.id = postLike.getId();
		this.userId = postLike.getUser().getId();
		this.postId = postLike.getPost().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
}
