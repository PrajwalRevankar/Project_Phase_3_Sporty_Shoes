package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Admin;

@Repository
public interface SportyRepository extends JpaRepository<Admin, Integer>{
	
	@Query("from Admin where username=?1 and password=?2")
	Admin findByUsernameandPassword(String username, String Password);

	public Admin getUserByUsername(String userName);
}
