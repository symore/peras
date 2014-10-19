package com.pitf.peras.security.spring.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pitf.peras.security.domain.User;

public class PerasUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private UserDetails springUserDetails;
	private User user;

	public PerasUserDetails(UserDetails springUserDetails, User user) {
		super();
		this.springUserDetails = springUserDetails;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return springUserDetails.getAuthorities();
	}

	@Override
	public String getPassword() {
		return springUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return springUserDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return springUserDetails.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return springUserDetails.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return springUserDetails.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return springUserDetails.isEnabled();
	}

}
