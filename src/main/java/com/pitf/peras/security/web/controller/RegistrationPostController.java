package com.pitf.peras.security.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pitf.peras.security.domain.User;
import com.pitf.peras.security.facade.RegistrationFacade;
import com.pitf.peras.security.web.domain.RegistrationRequest;

@Controller
public class RegistrationPostController {
	@Value("${registrationPostController.successView}")
	private String successView;
	private RegistrationFacade registrationFacade;

	@Autowired
	public RegistrationPostController(RegistrationFacade registrationFacade) {
		super();
		this.registrationFacade = registrationFacade;
	}

	@RequestMapping("${registrationPostController}")
	public String showRegistration(RegistrationRequest registrationRequest,
			BindingResult bindingResult) {
		registrationFacade.registerUser(new User(null, registrationRequest
				.getPassword(), registrationRequest.getUsername()));
		return successView;
	}
}
