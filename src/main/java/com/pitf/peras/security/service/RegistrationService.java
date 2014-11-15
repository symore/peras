package com.pitf.peras.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pitf.peras.security.domain.User;
import com.pitf.peras.user.dao.UserDao;
import com.pitf.peras.user.dao.domain.UserEntity;

@Component
public class RegistrationService {
	private UserDao userDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public RegistrationService(UserDao userDao, PasswordEncoder passwordEncoder) {
		super();
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerUser(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setUsername(user.getUsername());
		userEntity.setAuthority("USER");
		userEntity.setEnabled(true);
		userDao.save(userEntity);
	}

}
