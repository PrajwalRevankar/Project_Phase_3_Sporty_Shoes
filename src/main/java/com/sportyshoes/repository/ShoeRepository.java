package com.sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Shoes;

@Repository
public interface ShoeRepository extends JpaRepository<Shoes, Integer>{

}
