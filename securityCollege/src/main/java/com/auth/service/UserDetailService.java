package com.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.dao.RepositoryAdmission;
import com.auth.model.SecurityStudent;
import com.auth.model.StudentAdmission;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private RepositoryAdmission repositoryAdmission;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		StudentAdmission student = repositoryAdmission.findByName(username);
		System.out.println("user name from Security login");
		System.out.println(student.getFirstname() + "," + student.getPassword());
		SecurityStudent secure = null;
		if (student != null) {
			secure = new SecurityStudent();
			System.out.println("if.." + student.getFirstname());
			secure.setStudent(student);
		} else {
			throw new UsernameNotFoundException("User Not Found " + username + "");
		}

		return secure;
	}

}
