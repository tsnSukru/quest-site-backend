package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.Post;
import com.project.questsite.request.PostCreateRequest;
import com.project.questsite.request.PostDeleteRequest;
import com.project.questsite.request.PostUpdateRequest;

public interface IPostService {
	List<Post> getAll();

	Post getById(Long id);

	List<Post> getByUserId(Long userId);

	String add(PostCreateRequest postCreateRequest);

	String update(PostUpdateRequest postUpdateRequest);

	void delete(PostDeleteRequest postDeleteRequest);
}
