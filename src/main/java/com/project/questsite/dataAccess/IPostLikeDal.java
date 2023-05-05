package com.project.questsite.dataAccess;

import java.util.List;

import com.project.questsite.entities.PostLike;

public interface IPostLikeDal {
	List<PostLike> getAll(Long postId);

	PostLike getById(Long id);

	void add(PostLike postLike);

	void delete(PostLike postLike);
}
