package com.pitf.peras.security.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pitf.peras.security.web.domain.RegistrationRequest;

@Controller
public class RegistrationFormController {
	@Value("${registrationFormController.successView}")
	private String successView;

	@ModelAttribute("registrationRequest")
	public RegistrationRequest createRegistrationRequest() {
		return new RegistrationRequest();
	}

	@RequestMapping("${registrationFormController}")
	public String showRegistration() {
		return successView;
	}
}
