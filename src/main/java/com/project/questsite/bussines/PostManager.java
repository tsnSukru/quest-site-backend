package com.project.questsite.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.IPostDal;
import com.project.questsite.entities.Post;

@Service
public class PostManager implements IPostService {

	IPostDal postDal;

	@Autowired
	public PostManager(IPostDal postDal) {
		// TODO Auto-generated constructor stub
		this.postDal = postDal;
	}

	@Override
	@Transactional
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		return postDal.getAll();
	}

	@Override
	@Transactional
	public Post getById(Long id) {
		// TODO Auto-generated method stub
		return postDal.getById(id);
	}

	@Override
	@Transactional
	public void add(Post post) {
		// TODO Auto-generated method stub
		postDal.add(post);
	}

	@Override
	@Transactional
	public void update(Post post) {
		// TODO Auto-generated method stub
		postDal.update(post);
	}

	@Override
	@Transactional
	public void delete(Post post) {
		// TODO Auto-generated method stub
		postDal.delete(post);
	}

}
