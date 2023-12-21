package com.jwps.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwps.thymeleaf.model.Professor;


public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	List<Professor> findByNomeContainingIgnoreCase(String nome);

}
