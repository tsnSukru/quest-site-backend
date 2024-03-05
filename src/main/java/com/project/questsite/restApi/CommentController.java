package com.project.questsite.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questsite.bussines.ICommentService;
import com.project.questsite.entities.Comment;
import com.project.questsite.requests.CommentCreateRequest;
import com.project.questsite.requests.CommentDeleteRequest;
import com.project.questsite.requests.CommentUpdateRequest;
import com.project.questsite.responses.CommentResponse;

@RestController
@RequestMapping("/commentApi")
public class CommentController {
	ICommentService commentService;

	@Autowired
	public CommentController(ICommentService commentService) {
		// TODO Auto-generated constructor stub
		this.commentService = commentService;
	}

	@GetMapping("/comments/{postId}")
	@Transactional
	public List<CommentResponse> getAll(@PathVariable Long postId) {
		return commentService.getAll(postId);
	}

	@PostMapping("/add")
	@Transactional
	public void add(@RequestBody CommentCreateRequest commentCreateRequest) {
		commentService.add(commentCreateRequest);
	}

	@PostMapping("/update")
	@Transactional
	public void update(@RequestBody CommentUpdateRequest commentUpdateRequest) {
		commentService.update(commentUpdateRequest);
	}

	@PostMapping("/delete")
	@Transactional
	public void delete(@RequestBody CommentDeleteRequest commentDeleteRequest) {
		commentService.delete(commentDeleteRequest);
	}
}
