package com.pitf.peras.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.user.dao.domain.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}
