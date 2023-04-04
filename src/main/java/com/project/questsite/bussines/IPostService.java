package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.Post;

public interface IPostService {
	List<Post> getAll();

	Post getById(Long id);

	void add(Post post);

	void update(Post post);

	void delete(Post post);
}
