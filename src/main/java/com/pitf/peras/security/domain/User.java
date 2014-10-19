package com.pitf.peras.security.domain;

public class User {
	private Long userId;
	private String password;
	private String username;

	public User(Long userId, String password, String username) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}
