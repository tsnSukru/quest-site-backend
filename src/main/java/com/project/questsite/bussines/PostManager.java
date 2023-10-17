package com.project.questsite.bussines;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.IPostDal;
import com.project.questsite.entities.Post;
import com.project.questsite.entities.User;
import com.project.questsite.requests.PostCreateRequest;
import com.project.questsite.requests.PostDeleteRequest;
import com.project.questsite.requests.PostUpdateRequest;
import com.project.questsite.responses.PostLikeResponse;
import com.project.questsite.responses.PostResponse;

@Service
public class PostManager implements IPostService {

	IPostDal postDal;
	IPostLikeService postLikeService;
	IUserService userManager;

	@Autowired
	public PostManager(IPostDal postDal, IPostLikeService postLikeService, IUserService userManager) {
		// TODO Auto-generated constructor stub
		this.postDal = postDal;
		this.postLikeService = postLikeService;
		this.userManager = userManager;
	}

	@Override
	@Transactional
	public List<PostResponse> getAll() {
		// TODO Auto-generated method stub
		List<Post> posts = postDal.getAll();
		return posts.stream().map(p -> {
			List<PostLikeResponse> postLikeResponses = postLikeService.getAll(p.getId());
			return new PostResponse(p, postLikeResponses);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<PostResponse> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<Post> posts = postDal.getAll();
		return posts.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PostResponse getById(Long id) {
		// TODO Auto-generated method stub
		PostResponse postResponse = new PostResponse(postDal.getById(id));
		return postResponse;
	}

	@Override
	@Transactional
	public String add(PostCreateRequest postCreateRequest) {
		// TODO Auto-generated method stub
		User user = userManager.getById(postCreateRequest.userId);
		if (user == null) {
			return "Kullanici yok";
		}
		Post post = new Post.Builder().setUser(user).setText(postCreateRequest.text).setTitle(postCreateRequest.title)
				.setCreateDate(new Date()).build();
		postDal.add(post);
		return "Post Eklendi";
	}

	@Override
	@Transactional
	public String update(PostUpdateRequest postUpdateRequest) {
		// TODO Auto-generated method stub
		Post post = postDal.getById(postUpdateRequest.id);
		if (post == null) {
			return "Post yok";
		}

		post.setText(postUpdateRequest.text);
		post.setTitle(postUpdateRequest.title);
		postDal.update(post);
		return "Post Guncellendi";
	}

	@Override
	@Transactional
	public void delete(PostDeleteRequest postDeleteRequest) {
		// TODO Auto-generated method stub
		Post post = postDal.getById(postDeleteRequest.id);
		if (post == null) {
			System.out.println("post yok");
		} else {
			postDal.delete(post);
		}
	}

}
