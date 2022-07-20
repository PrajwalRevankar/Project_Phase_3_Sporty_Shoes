package com.sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Category;

@Repository
public interface CatRepository extends JpaRepository<Category, Integer>{
	
}
