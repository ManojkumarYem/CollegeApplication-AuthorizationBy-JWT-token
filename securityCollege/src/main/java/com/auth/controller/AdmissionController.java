package com.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.StudentAdmission;
import com.auth.service.AdmissionServices;

@CrossOrigin
@RestController
public class AdmissionController {
	public AdmissionController() {
		System.out.println("Admission Controller Object Created");
	}

	@Autowired
	AdmissionServices service;
//Admission Form add in Database

	@PostMapping("/registerUser")
	public String saveStudents(StudentAdmission stuAd, @RequestParam(value = "registerId") String regId) {
		try {
			service.saveAdmission(stuAd, regId);
		} catch (Exception e) {
			e.getMessage();
		}
		return "Success";
	}

//view Admission Records from Database:

	@PostMapping("/viewAdmissionRecords")
	public List<StudentAdmission> viewAdmissionRecord() {
		List<StudentAdmission> admissiontDetails = null;
		try {
			admissiontDetails = service.getAllAdmission();

		} catch (Exception e) {
			e.printStackTrace();
//			return admissiontDetails;
		}
		return admissiontDetails;
	}

	@GetMapping("/getLastApplicationId")
	public String getLastAppId() {
		String lastAppId = service.lastAppId();
		return lastAppId;
	}

	@GetMapping("/checkUserName")
	public String checkUserName(@RequestParam("username") String username) {
		System.out.println(username);
		String userName = service.checkUserName(username);

		return userName;

	}

	@GetMapping("/removingStudent")
	public String removeStudent(@RequestParam("registerId") String id) {
		service.removeStudent(id);
		return "success";

	}

}
