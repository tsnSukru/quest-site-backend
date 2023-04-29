package com.project.questsite.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questsite.bussines.IPostService;
import com.project.questsite.entities.Post;
import com.project.questsite.request.PostCreateRequest;
import com.project.questsite.request.PostDeleteRequest;
import com.project.questsite.request.PostUpdateRequest;

@RestController
@RequestMapping("/postApi")
public class PostController {
	IPostService postService;

	@Autowired
	public PostController(IPostService postService) {
		// TODO Auto-generated constructor stub
		this.postService = postService;
	}

	@GetMapping("/posts")
	public List<Post> getAll() {
		return postService.getAll();
	}

	@GetMapping("/posts/user/{userid}")
	public List<Post> getByUserId(@PathVariable Long userid) {
		return postService.getByUserId(userid);
	}

	@GetMapping("/posts/{id}")
	public Post getById(@PathVariable Long id) {
		return postService.getById(id);
	}

	@PostMapping("/add")
	public void add(@RequestBody PostCreateRequest postCreateRequest) {
		postService.add(postCreateRequest);
	}

	@PostMapping("/update")
	public void update(@RequestBody PostUpdateRequest postUpdateRequest) {
		postService.update(postUpdateRequest);
	}

	@PostMapping("/delete")
	public void delete(@RequestBody PostDeleteRequest postDeleteRequest) {
		postService.delete(postDeleteRequest);
	}
}
