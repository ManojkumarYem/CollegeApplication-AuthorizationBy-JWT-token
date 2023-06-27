package com.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.auth.model.StudentAdmission;

public interface RepositoryAdmission extends JpaRepository<StudentAdmission, Integer> {

	Optional<StudentAdmission> findByregisterId(String id);

//	FINDING LAST REGISTERATION ID
	@Query(value = "SELECT application_Id FROM Student_Admission  ORDER BY application_Id DESC LIMIT 1", nativeQuery = true)
	String findLastApplicationId();

	@Query(value = "select username from Student_Admission where username=:userName", nativeQuery = true)
	String userName(@Param("userName") String userName);

	@Modifying
	@Transactional
	@Query(value = "update student_admission set active=0 where register_id=:regId", nativeQuery = true)
	void RemoveStudent(@Param("regId") String id);

	@Query(value = "select count(*) from student_admission where active=1", nativeQuery = true)
	String totalActiveStudents();

	@Query(value = "select count(*) from student_admission where active=0", nativeQuery = true)
	String totalInActiveStudents();

	StudentAdmission findByName(String username);

	StudentAdmission findByUsername(String username);

}

//String saveAdmission();
//@Query(value = "Insert into student_admission(registerId, applicationId, firstname, lastname, dateOfBirth, age, email, mobilenumber, graduate, department, course, address, city, pincode, state, country, username, password,cutoff) values(:regId, :appId, :fname, :lName, :dateOfBirth, :age, :mail, :mobile, :graduate, :dept, :course, :address, :city, :pin, :state, :country, :username, :pass, :cuttOff)", nativeQuery = true)
//void saveAdmission(@Param("regId") String regId,@Param("appId") String appId,@Param("name") String name,@Param("graduate") String graduate,@Param("dept") String dept,@Param("course") String course,@Param("username")
//		String username,@Param("pass") String pass,@Param("address") String address,@Param("age") int age,@Param("city") String city,@Param("country") String country,@Param("cutOff") int cutOff,@Param("dateOfBirth")
//		String dateOfBirth,@Param("mail") String mail,@Param("fName") String fName,@Param("lName") String lName,@Param("mobile") long mobile,@Param("pin") String pin,@Param("state") String state);
//@Query(value = "select * from student_admission cross join student_application on student_admission.register_id=student_application.register_id", nativeQuery = true)
//List findAllAdmission();
