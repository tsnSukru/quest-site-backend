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

	@GetMapping("/posts/{id}")
	public Post getById(@PathVariable long id) {
		return postService.getById(id);
	}

	@PostMapping("/add")
	public void add(@RequestBody Post post) {
		postService.add(null);
	}

	@PostMapping("/update")
	public void update(@RequestBody Post post) {
		postService.update(post);
	}

	@PostMapping("/delete")
	public void delete(@RequestBody Post post) {
		postService.delete(post);
	}
}
