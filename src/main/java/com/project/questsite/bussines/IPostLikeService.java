package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.PostLike;
import com.project.questsite.request.PostLikeCreateRequest;
import com.project.questsite.request.PostLikeDeleteRequest;

public interface IPostLikeService {
	List<PostLike> getAll(Long postId);

	PostLike getById(Long id);

	void add(PostLikeCreateRequest postLikeCreateRequest);

	void delete(PostLikeDeleteRequest postLikeDeleteRequest);
}
