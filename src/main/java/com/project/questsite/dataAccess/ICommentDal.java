package com.project.questsite.dataAccess;

import java.util.List;

import com.project.questsite.entities.Comment;

public interface ICommentDal {
	List<Comment> getAll(Long postId);

	Comment getById(Long id);

	void add(Comment comment);

	void update(Comment comment);

	void delete(Comment comment);
}
