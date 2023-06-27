package com.auth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.auth.dao.RepositoryAdmission;
import com.auth.model.StudentAdmission;
import com.auth.service.UserDetailService;

//@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserDetailService Service;

	@Autowired
	private RepositoryAdmission repositoryAdmission;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String redirectUrl = null;
		if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
			DefaultOAuth2User userDetails = (DefaultOAuth2User) authentication.getPrincipal();
			String username = userDetails.getAttribute("email") != null ? userDetails.getAttribute("email")
					: userDetails.getAttribute("login") + "@gmail.com";
			System.out.println(userDetails.getAttribute("email").toString());

			StudentAdmission studentAdmission = new StudentAdmission();
			studentAdmission.setName(username);
			studentAdmission.setPassword("Oauth");
			studentAdmission.setActive("1");
			repositoryAdmission.save(studentAdmission);
			Service.loadUserByUsername(username);

			redirectUrl = "http://127.0.0.1:5500/Task%209/index.html";

		}
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}

}