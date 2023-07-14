package com.project.questsite.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questsite.bussines.IUserService;
import com.project.questsite.entities.User;

@RestController
@RequestMapping("/userApi")
public class UserController {
	private IUserService userService;

	@Autowired
	public UserController(IUserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> getAll() {
		return userService.getAll();
	}

	@GetMapping("/users/{id}")
	public User getById(@PathVariable Long id) {
		return userService.getById(id);
	}

	@GetMapping("/user/{username}")
	public User getByUsername(@PathVariable String username) {
		return userService.getByUserName(username);
	}

	@PostMapping("/add")
	public void addUser(@RequestBody User user) {
		userService.add(user);
	}

	@PostMapping("/update")
	public void updateUser(@RequestBody User user) {
		userService.update(user);
	}

	@PostMapping("/delete")
	public void deleteUser(@RequestBody User user) {
		userService.delete(user);
	}
}
