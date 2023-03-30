package com.project.questsite.dataAccess;

import java.util.List;

import com.project.questsite.entities.User;

public interface IUserDal {
	List<User> getAll();

	User getById(Long id);

	void add(User user);

	void update(User user);

	void delete(User user);
}
