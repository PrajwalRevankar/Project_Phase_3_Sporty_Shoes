package com.sportyshoes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	public List<Report> findByCategory(String category);
	public List<Report> findByDop(Date date);

}
