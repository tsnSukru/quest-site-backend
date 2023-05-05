package com.project.questsite.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.IPostLikeDal;
import com.project.questsite.entities.Post;
import com.project.questsite.entities.PostLike;
import com.project.questsite.entities.User;
import com.project.questsite.request.PostLikeCreateRequest;
import com.project.questsite.request.PostLikeDeleteRequest;

@Service
public class PostLikeManager implements IPostLikeService {
	IPostLikeDal postLikeDal;
	IUserService userManager;
	IPostService postManager;

	@Autowired
	public PostLikeManager(IPostLikeDal postLikeDal, IUserService userManager, IPostService postManager) {
		// TODO Auto-generated constructor stub
		this.postLikeDal = postLikeDal;
		this.userManager = userManager;
		this.postManager = postManager;
	}

	@Override
	@Transactional
	public List<PostLike> getAll(Long postId) {
		// TODO Auto-generated method stub
		return postLikeDal.getAll(postId);
	}

	@Override
	@Transactional
	public PostLike getById(Long id) {
		// TODO Auto-generated method stub
		return postLikeDal.getById(id);
	}

	@Override
	@Transactional
	public void add(PostLikeCreateRequest postLikeCreateRequest) {
		// TODO Auto-generated method stub
		User user = userManager.getById(postLikeCreateRequest.userId);
		Post post = postManager.getById(postLikeCreateRequest.postId);
		if (user == null || post == null) {
			System.out.println("Post ya da kullanici yok");
		} else {
			PostLike postLike = new PostLike();
			postLike.setUser(user);
			postLike.setPost(post);
			postLikeDal.add(postLike);
		}
	}

	@Override
	@Transactional
	public void delete(PostLikeDeleteRequest postLikeDeleteRequest) {
		// TODO Auto-generated method stub
		PostLike postLike = postLikeDal.getById(postLikeDeleteRequest.id);
		if (postLike == null) {
			System.out.println("begeni yok");
		} else {
			postLikeDal.delete(postLike);
		}
	}

}
