package com.jwps.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwps.thymeleaf.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
