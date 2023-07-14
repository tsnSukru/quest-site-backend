package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.requests.PostCreateRequest;
import com.project.questsite.requests.PostDeleteRequest;
import com.project.questsite.requests.PostUpdateRequest;
import com.project.questsite.responses.PostResponse;

public interface IPostService {
	List<PostResponse> getAll();

	PostResponse getById(Long id);

	List<PostResponse> getByUserId(Long userId);

	String add(PostCreateRequest postCreateRequest);

	String update(PostUpdateRequest postUpdateRequest);

	void delete(PostDeleteRequest postDeleteRequest);
}
