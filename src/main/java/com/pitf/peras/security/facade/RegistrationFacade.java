package com.pitf.peras.security.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pitf.peras.security.domain.User;
import com.pitf.peras.security.service.RegistrationService;

@Component
public class RegistrationFacade {
	private RegistrationService registrationService;

	@Autowired
	public RegistrationFacade(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}

	public void registerUser(User user) {
		registrationService.registerUser(user);
	}
}
