package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.Comment;
import com.project.questsite.requests.CommentCreateRequest;
import com.project.questsite.requests.CommentDeleteRequest;
import com.project.questsite.requests.CommentUpdateRequest;

public interface ICommentService {
	List<Comment> getAll(Long postId);

	Comment getById(Long id);

	void add(CommentCreateRequest commentCreateRequest);

	void update(CommentUpdateRequest commentUpdateRequest);

	void delete(CommentDeleteRequest commentDeleteRequest);
}
