package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.Comment;
import com.project.questsite.request.CommentCreateRequest;
import com.project.questsite.request.CommentDeleteRequest;
import com.project.questsite.request.CommentUpdateRequest;

public interface ICommentService {
	List<Comment> getAll(Long postId);

	Comment getById(Long id);

	void add(CommentCreateRequest commentCreateRequest);

	void update(CommentUpdateRequest commentUpdateRequest);

	void delete(CommentDeleteRequest commentDeleteRequest);
}
