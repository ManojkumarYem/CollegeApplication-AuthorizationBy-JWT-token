package com.auth.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dao.ApiResponse;
import com.auth.model.StudentApplication;
import com.auth.service.CollegeServices;

import com.auth.service.JWTUtillity;
import com.auth.service.UserDetailService;

@CrossOrigin // for save from crossError
@RestController
public class CollegeController {
	@Autowired
	private JWTUtillity utillity;

	@Autowired
	private UserDetailService Service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ApiResponse response;

	public CollegeController() {
		System.out.println("Controller Object Created");
	}

	@Autowired
	CollegeServices service;

	@GetMapping("/")
	public void home(HttpServletResponse res) throws IOException {

		res.sendRedirect("http://127.0.0.1:5500/Task%209/index.html");
	}

//	Application Form add in Database:

	@PostMapping("/saveApplicationID")
	public String saveStudents(StudentApplication stu) {

		try {
			service.saveInDB(stu);
		} catch (Exception e) {
			e.getMessage();
		}
		return "Success";
	}

//	view Application Records from Database:

	@GetMapping("/viewStudentRecords")
	public List<StudentApplication> viewStudentRecords() {
		List<StudentApplication> studentDetails = null;
		try {
			studentDetails = service.getAllStudents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentDetails;

	}

	/************************
	 * Fetching Student Details Function
	 ************************/

	@GetMapping("/ApplicationForm")
	public StudentApplication getStudentByRegisterId(@RequestParam("registerId") String id) {
		StudentApplication studentDetails = service.getStudentById(id);
		return studentDetails;

	}

	@GetMapping("/getLastStudentRegisterId")
	public String getLastStudentRegId() {
		System.out.println("home");
		String lastRegId = service.getLastStudentRegId();

		return lastRegId;
	}

	// get specific column:
	@GetMapping("/getAllRegisterId")
	public List<String> getAllRegisterId() {
		List<String> allList = null;
		allList = service.getAllRegistertId();
		return allList;

	}

	@PostMapping("/authenticate")
	public ApiResponse authenticate(JWTRequest jwtRequest, HttpSession session, HttpServletRequest req)
			throws Exception {
		ApiResponse response = new ApiResponse();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
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

		return response;
	}

//	@ExceptionHandler(value = Exception.class)
//	public Object APIException(Exception ex) {
//		return ex;
//
//	}

	@PostMapping("/logoutt")
	public String logOut() {
		System.out.println("logout");
		return "Success";
	}

	@GetMapping("/GetCurrentUser")
	public String User(Principal principal) {
		String user = principal.getName();
		System.out.println(user);
		return user;
	}

}

//@GetMapping("/getLastStudentRegisterId")
//public  List<StudentApplication> getLastStudentRegisterId() {
//	
//	 List<StudentApplication>lastRegId = service.getLastRegisterId();
//
//	return lastRegId;
//}
//
//USING SESSION
//@PostMapping("/authenticate2")
//public String auth(HttpServletRequest req, HttpServletResponse res, @RequestParam("user") String user) {
//	System.out.println("authenticate2");
//	HttpSession session = req.getSession();
//	if (user.equals("manoj")) {
//		session.setAttribute("USER", user);
//		req.getSession().setMaxInactiveInterval(60); // in Seconds
//		return "Welcome " + user;
//	}
//
//	return "Please SignUp First Mr/Ms." + user;
//}