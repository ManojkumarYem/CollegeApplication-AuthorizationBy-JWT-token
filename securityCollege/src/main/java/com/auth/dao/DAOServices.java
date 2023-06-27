package com.auth.dao;

import org.springframework.stereotype.Service;

@Service
public class DAOServices {
	
	private Repository repo;

	private RepositoryAdmission admissionRepo;

	public DAOServices(RepositoryAdmission admissionRepo, Repository repo) { // constructor based BEAN
																					// Inizialization
		this.admissionRepo = admissionRepo;
		this.repo = repo;
	}

	public String totalNoOfStudents() {
		return repo.totelStudents();
	}

	public String totalActiveStudents() {
		return admissionRepo.totalActiveStudents();
	}

	public String inActiveStudents() {
		return admissionRepo.totalInActiveStudents();
	}
	
	

}
