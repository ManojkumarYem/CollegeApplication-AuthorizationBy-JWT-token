package com.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auth.model.StudentApplication;

public interface Repository extends JpaRepository<StudentApplication, Integer> {

	StudentApplication findByregisterId(String id);

//	FINDING LAST REGISTERATION ID
	@Query(value = "SELECT register_Id FROM Student_Application  ORDER BY register_id DESC LIMIT 1", nativeQuery = true)
	String findLastRegisterId();

	@Query(value = "select count(*) from student_application", nativeQuery = true)
	String totelStudents();

}
//@Query(value = "SELECT register_Id FROM Student_Application", nativeQuery = true)
//Iterable<StudentApplication> getAllList();
