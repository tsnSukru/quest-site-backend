package com.project.questsite.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.questsite.entities.User;
import com.project.questsite.security.JwtUserDetails;

@Service
public class UserDetailsManagerImpl implements UserDetailsService {

	private IUserService userManager;

	@Autowired
	public UserDetailsManagerImpl(IUserService userManager) {
		this.userManager = userManager;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userManager.getByUserName(username);
		return JwtUserDetails.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = userManager.getById(id);
		return JwtUserDetails.create(user);
	}
}
