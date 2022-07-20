package com.sportyshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.Admin;
import com.sportyshoes.repository.SportyRepository;

@Service
public class SportyService {
	
	@Autowired
	SportyRepository sportyrepo;
	
	public Admin Login(String username, String password) {
		Admin a = sportyrepo.findByUsernameandPassword(username, password);
		return a;
	}
	public Admin getUserByUsername(String userName) {
		return sportyrepo.getUserByUsername(userName);
	}

}
