package com.project.questsite.dataAccess;

import java.util.List;

import com.project.questsite.entities.Post;

public interface IPostDal {
	List<Post> getAll();

	List<Post> getByUserId(Long userId);

	Post getById(Long id);

	void add(Post post);

	void update(Post post);

	void delete(Post post);

}
