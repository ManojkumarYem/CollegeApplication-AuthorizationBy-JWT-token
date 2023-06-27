package com.auth.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dao.ApiResponse;
import com.auth.service.CollegeServices;
import com.auth.service.JWTUtillity;
import com.auth.service.UserDetailService;

//@Controller
public class PageController {

	@Autowired
	private JWTUtillity utillity;

	@Autowired
	private UserDetailService Service;

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private ApiResponse response;

	@Autowired
	CollegeServices service;

	@GetMapping("/")
	public String index() {
//		Map<String, Object> claims = new HashMap<>();
//		String token = utillity.doGenerateToken(claims, principal.getName());
//		System.out.println(token);
//		m.addAttribute("Access", token);
		System.out.println("Oauth");
		return "index";
	}

	@GetMapping("/css/otpStyle.css")
	public String index5(Model m, Principal principal) {
//		Map<String, Object> claims = new HashMap<>();
//		String token = utillity.doGenerateToken(claims, principal.getName());
//		System.out.println(token);
//		m.addAttribute("Access", token);
		System.out.println("Oauth");
		return "index";
	}

	@GetMapping("/page/login")
	public String index1() {
		System.out.println("login");
		return "login.html";
	}

	@GetMapping("/page/home")
	public String index2() {
		System.out.println("login");
		return "index.html";
	}

	@GetMapping("/page/viewApplication")
	public String viewApplication() {
		System.out.println("login1");
		return "viewStudentApplication";
	}

	@GetMapping("/page/viewAddmission")
	public String viewAddmission() {
		System.out.println("login1");
		return "viewAddmission";
	}

	@GetMapping("/page/AddAdmission")
	public String addAddmission() {
		System.out.println("login1");
		return "addAddmission";
	}

	@GetMapping("/page/AddApplication")
	public String addApplication() {
		System.out.println("login1");
		return "addApplication";
	}

	@GetMapping("/page/remove")
	public String removeStudent() {
		System.out.println("login1");
		return "remove";
	}
//	@GetMapping("/changePass")
//	public String changePassword() {
//		System.out.println("login1");
//		return "addApplication";
//	}
//	@GetMapping("/logout")
//	public String logout() {
//		System.out.println("login1");
//		return "addApplication";
//	}

	@PostMapping("/authenticate1")
	public String authenticate(JWTRequest jwtRequest, HttpSession session, HttpServletRequest req) throws Exception {
		ApiResponse response = new ApiResponse();
		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		final UserDetails userDetails = Service.loadUserByUsername(jwtRequest.getUserName());
		final String token = utillity.generateToken(userDetails);
//		if (token != null) {
//			req.getSession();
//			session.setAttribute("UserToken", jwtRequest.getUserName());
//		}
		response.setData(token);
		System.out.println();
//		response.setError(principal.getName());

		return "index";
	}

}
