package com.project.questsite.dataAccess;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.entities.PostLike;

import jakarta.persistence.EntityManager;

@Repository
public class PostLikeDal implements IPostLikeDal {

	EntityManager entityManager;

	@Autowired
	public PostLikeDal(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<PostLike> getAll(Long postId) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		List<PostLike> postLikes = session.createQuery("from PostLike as E where E.post.id=" + postId, PostLike.class)
				.getResultList();
		return postLikes;
	}

	@Override
	public PostLike getById(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		PostLike postLike = session.get(PostLike.class, id);
		return postLike;
	}

	@Override
	public void add(PostLike postLike) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.merge(postLike);
	}

	@Override
	public void delete(PostLike postLike) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.remove(postLike);
	}

}
