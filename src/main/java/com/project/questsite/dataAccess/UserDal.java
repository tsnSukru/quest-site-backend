package com.project.questsite.dataAccess;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.entities.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserDal implements IUserDal {

	EntityManager entityManager;

	@Autowired
	public UserDal(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<User> getAll() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		List<User> users = session.createQuery("from User", User.class).getResultList();
		return users;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, id);
		return user;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		User userToDelete = session.get(User.class, user.getId());
		session.delete(userToDelete);
	}

}
