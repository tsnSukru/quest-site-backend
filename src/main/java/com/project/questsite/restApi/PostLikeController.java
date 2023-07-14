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

import com.project.questsite.bussines.IPostLikeService;
import com.project.questsite.requests.PostLikeCreateRequest;
import com.project.questsite.requests.PostLikeDeleteRequest;
import com.project.questsite.responses.PostLikeResponse;

@RestController
@RequestMapping("/postLikeApi")
public class PostLikeController {

	IPostLikeService postLikeService;

	@Autowired
	public PostLikeController(IPostLikeService postLikeService) {
		// TODO Auto-generated constructor stub
		this.postLikeService = postLikeService;
	}

	@GetMapping("/postLikes/{postId}")
	@Transactional
	public List<PostLikeResponse> getAll(@PathVariable Long postId) {
		return postLikeService.getAll(postId);
	}

	@PostMapping("/add")
	@Transactional
	public void add(@RequestBody PostLikeCreateRequest postLikeCreateRequest) {
		postLikeService.add(postLikeCreateRequest);
	}

	@PostMapping("/delete")
	@Transactional
	public void delete(@RequestBody PostLikeDeleteRequest postLikeDeleteRequest) {
		postLikeService.delete(postLikeDeleteRequest);
	}
}
