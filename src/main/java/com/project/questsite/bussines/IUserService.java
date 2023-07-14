package com.project.questsite.bussines;

import java.util.List;

import com.project.questsite.entities.User;

public interface IUserService {
	List<User> getAll();

	User getById(Long id);

	User getByUserName(String userName);

	void add(User user);

	void update(User user);

	void delete(User user);

}
