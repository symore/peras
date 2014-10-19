package com.pitf.peras.security.service;

import com.pitf.peras.security.domain.User;

public interface AuthenticationService {
	public User getCurrentUser();
}
