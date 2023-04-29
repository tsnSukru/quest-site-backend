package com.project.questsite.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.IPostDal;
import com.project.questsite.entities.Post;
import com.project.questsite.entities.User;
import com.project.questsite.request.PostCreateRequest;
import com.project.questsite.request.PostDeleteRequest;
import com.project.questsite.request.PostUpdateRequest;

@Service
public class PostManager implements IPostService {

	IPostDal postDal;
	IUserService userManager;

	@Autowired
	public PostManager(IPostDal postDal, IUserService userManager) {
		// TODO Auto-generated constructor stub
		this.postDal = postDal;
		this.userManager = userManager;
	}

	@Override
	@Transactional
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		return postDal.getAll();
	}

	@Override
	@Transactional
	public List<Post> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return postDal.getByUserId(userId);
	}

	@Override
	@Transactional
	public Post getById(Long id) {
		// TODO Auto-generated method stub
		return postDal.getById(id);
	}

	@Override
	@Transactional
	public String add(PostCreateRequest postCreateRequest) {
		// TODO Auto-generated method stub
		User user = userManager.getById(postCreateRequest.userId);
		if (user == null) {
			return "Kullanici yok";
		}
		Post post = new Post();
		post.setId(postCreateRequest.id);
		post.setText(postCreateRequest.text);
		post.setTitle(postCreateRequest.title);
		post.setUser(user);
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
