package com.project.questsite.responses;

import java.util.List;

import com.project.questsite.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
	public Long id;
	public Long userId;
	public String userName;
	public String title;
	public String text;
	public List<PostLikeResponse> postLikeResponses;

	public PostResponse(Post post, List<PostLikeResponse> postLikeResponses) {
		// TODO Auto-generated constructor stub
		this.id = post.getId();
		this.userId = post.getUser().getId();
		this.userName = post.getUser().getUserName();
		this.title = post.getTitle();
		this.text = post.getText();
		this.postLikeResponses = postLikeResponses;
	}

	public PostResponse(Post post) {
		// TODO Auto-generated constructor stub
		this.id = post.getId();
		this.userId = post.getUser().getId();
		this.userName = post.getUser().getUserName();
		this.title = post.getTitle();
		this.text = post.getText();
		this.postLikeResponses = null;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<PostLikeResponse> getPostLikeResponses() {
		return postLikeResponses;
	}

	public void setPostLikeResponses(List<PostLikeResponse> postLikeResponses) {
		this.postLikeResponses = postLikeResponses;
	}

}
