package com.pitf.peras.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pitf.peras.security.domain.User;
import com.pitf.peras.security.spring.domain.PerasUserDetails;
import com.pitf.peras.user.dao.UserDao;
import com.pitf.peras.user.dao.domain.UserEntity;

public class DefaultAuthenticationService implements AuthenticationService,
		UserDetailsService {
	private UserDetailsService userdetailsService;
	private UserDao userDao;

	@Autowired
	public DefaultAuthenticationService(UserDetailsService userdetailsService,
			UserDao userDao) {
		super();
		this.userdetailsService = userdetailsService;
		this.userDao = userDao;
	}

	@Override
	public User getCurrentUser() {
		PerasUserDetails userDetails = (PerasUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails.getUser();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails userDetails = userdetailsService
				.loadUserByUsername(username);
		UserEntity userEntity = userDao.findByUsername(username);
		return new PerasUserDetails(userDetails, createUser(userEntity));
	}

	private User createUser(UserEntity userEntity) {
		return new User(userEntity.getUserId(), userEntity.getPassword(),
				userEntity.getUsername());
	}
}
