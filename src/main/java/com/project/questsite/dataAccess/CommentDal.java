package com.project.questsite.dataAccess;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.entities.Comment;

import jakarta.persistence.EntityManager;

@Repository
public class CommentDal implements ICommentDal {

	EntityManager entityManager;

	@Autowired
	public CommentDal(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Comment> getAll(Long postId) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		List<Comment> comments = session.createQuery("from Comment as E where E.post.id = " + postId, Comment.class)
				.getResultList();
		return comments;
	}

	@Override
	@Transactional
	public Comment getById(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Comment comment = session.get(Comment.class, id);
		return comment;
	}

	@Override
	@Transactional
	public void add(Comment comment) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.merge(comment);
	}

	@Override
	@Transactional
	public void update(Comment comment) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.merge(comment);
	}

	@Override
	@Transactional
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.remove(comment);
	}

}
