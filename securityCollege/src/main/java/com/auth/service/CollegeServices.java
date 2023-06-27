package com.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth.dao.Repository;
import com.auth.model.StudentApplication;

@Service
public class CollegeServices {

	private Repository repo;

	public CollegeServices(Repository repo) { // constructor based BEAN Inizialization
		this.repo = repo;
	}

	public StudentApplication saveInDB(StudentApplication stu) {

		return repo.save(stu);
	}

	public List<StudentApplication> getAllStudents() {
		List<StudentApplication> studentsList = (List<StudentApplication>) repo.findAll();
		return studentsList;
	}

	public StudentApplication getStudentById(String id) {

		StudentApplication studentsList = repo.findByregisterId(id);
		return studentsList;

	}

	public String getLastStudentRegId() {
		return repo.findLastRegisterId();
	}

	public List<String> getAllRegistertId() {
		List<String> getAllList = new ArrayList();
		Iterable<StudentApplication> iterable = repo.findAll();
		iterable.forEach(registerId -> getAllList.add(registerId.getRegisterId()));
		return getAllList;
	}

}
//public StudentApplicaton getLastStudentRegisterId() {
//
//return (StudentApplication) repo.findAll();
//}

//public String getLastRegisterId() {
//String houseId=null;
//List<StudentApplication> id=repo.findLastRegisterId();
//for(StudentApplication stu:id ) {
//	 houseId = stu.getRegisterId();
//	}
//	System.out.println(houseId);
//return houseId;
//}
