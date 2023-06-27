package com.auth.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth.dao.Repository;
import com.auth.dao.RepositoryAdmission;
import com.auth.model.StudentAdmission;
import com.auth.model.StudentApplication;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

@Service
public class AdmissionServices {

	private Repository repo2;

	private RepositoryAdmission admissionRepo;

	public AdmissionServices(RepositoryAdmission admissionRepo, Repository repo2) { // constructor based BEAN
																					// Inizialization
		this.admissionRepo = admissionRepo;
		this.repo2 = repo2;
	}

	public void saveAdmission(StudentAdmission stuAd, String id) {
		StudentApplication list = repo2.findByregisterId(id);
		stuAd.setFirstname(list.getFirstname());
		stuAd.setLastname(list.getLastname());
		stuAd.setDateOfBirth(list.getDateOfBirth());
		stuAd.setAge(list.getAge());
		stuAd.setAddress(list.getAddress());
		stuAd.setEmail(list.getEmail());
		stuAd.setMobilenumber(list.getMobilenumber());
		stuAd.setCity(list.getCity());
		stuAd.setCountry(list.getCountry());
		stuAd.setCutoff(list.getCutoff());
		stuAd.setPincode(list.getPincode());
		stuAd.setState(list.getState());
		stuAd.setActive("1");
		admissionRepo.save(stuAd);

	}

	public List<StudentAdmission> getAllAdmission() {

		List<StudentAdmission> admissionlist = admissionRepo.findAll();

		return admissionlist;
	}

	public String lastAppId() {
		return admissionRepo.findLastApplicationId();
	}

	public String checkUserName(String userName) {
		return admissionRepo.userName(userName);

	}

	public void removeStudent(String id) {
		admissionRepo.RemoveStudent(id);

	}

}
//public String saveInDB(HttpServletRequest req,String regId, String appId,
//String name, String graduate, String dept, String course, String username, String pass) {
//StudentApplication student = repo2.findByregisterId(regId);
//
//String address = student.getAddress();
//int age = student.getAge();
//String city = student.getCity();
//String country = student.getCountry();
//int cutOff = student.getCutoff();
//String DateOfBirth = student.getDateOfBirth();
//String mail = student.getEmail();
//String fName = student.getFirstname();
//String lName = student.getLastname();
//long mobile = student.getMobilenumber();
//String pin = student.getPincode();
//String state = student.getState();
//
//repo.saveAdmission(regId, appId, name, graduate, dept, course, username, pass, address, age, city,
//	country, cutOff, DateOfBirth, mail, fName, lName, mobile, pin, state);
//return "SSSSSSSSSS";
//}
