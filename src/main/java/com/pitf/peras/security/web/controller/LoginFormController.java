package com.pitf.peras.security.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pitf.peras.security.web.domain.LoginRequest;

@Controller
public class LoginFormController {
	@Value("${loginFormController.successView}")
	private String successView;

	@ModelAttribute("loginRequest")
	public LoginRequest createLoginRequest() {
		return new LoginRequest();
	}

	@RequestMapping("${loginFormController}")
	public String showLogin(
			@ModelAttribute("loginRequest") LoginRequest loginRequest,
			BindingResult bindingResult, HttpSession httpSession) {
		Throwable authException = (Throwable) httpSession
				.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (authException != null) {
			bindingResult.reject(authException.getMessage(),
					authException.getMessage());
			httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		return successView;
	}
}
