package com.project.questsite.dataAccess;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.entities.Post;

import jakarta.persistence.EntityManager;

@Repository
public class PostDal implements IPostDal {

	EntityManager entityManager;

	@Autowired
	public PostDal(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		List<Post> posts = session.createQuery("from Post", Post.class).getResultList();
		return posts;
	}

	@Override
	@Transactional
	public Post getById(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Post post = session.get(Post.class, id);
		return post;
	}

	@Override
	@Transactional
	public void add(Post post) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.merge(post);
	}

	@Override
	public void update(Post post) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.merge(post);
	}

	@Override
	public void delete(Post post) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.remove(post);
	}
}
