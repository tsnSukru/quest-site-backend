package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.PostLike;
import com.project.questsite.requests.PostLikeCreateRequest;
import com.project.questsite.requests.PostLikeDeleteRequest;
import com.project.questsite.responses.PostLikeResponse;

public interface IPostLikeService {
	List<PostLikeResponse> getAll(Long postId);

	PostLike getById(Long id);

	void add(PostLikeCreateRequest postLikeCreateRequest);

	void delete(PostLikeDeleteRequest postLikeDeleteRequest);
}
