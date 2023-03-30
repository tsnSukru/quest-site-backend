package com.project.questsite.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.IUserDal;
import com.project.questsite.entities.User;

@Service
public class UserManager implements IUserService {

	private IUserDal userDal;

	@Autowired
	public UserManager(IUserDal userDal) {
		// TODO Auto-generated constructor stub
		this.userDal = userDal;
	}

	@Override
	@Transactional
	public List<User> GetAll() {
		// TODO Auto-generated method stub
		return userDal.getAll();
	}

	@Override
	@Transactional
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userDal.getById(id);
	}

	@Override
	@Transactional
	public void add(User user) {
		// TODO Auto-generated method stub
		userDal.add(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		userDal.update(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		// TODO Auto-generated method stub
		userDal.delete(user);
	}
}
