package com.jwps.thymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwps.thymeleaf.exception.PessoaNotFoundException;
import com.jwps.thymeleaf.model.Professor;
import com.jwps.thymeleaf.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public Professor criarProfessor(Professor professor) {
		
		return professorRepository.save(professor);
	}
	
	public List<Professor> listarProfessores(){
		return professorRepository.findAll();
	}
	
	public Professor buscarProfessorPorCodigo(Long codigo) throws PessoaNotFoundException {
		Optional<Professor> professorEncotrado = professorRepository.findById(codigo);
		
		if(professorEncotrado.isPresent()) {
			return professorEncotrado.get();
		}else {
			throw new PessoaNotFoundException("Professor com código " + codigo + " não foi encontrado.");
		}	
	}
	
	public void excluirProfessorPorCodigo(Long codigo) throws PessoaNotFoundException {
		Professor professor = buscarProfessorPorCodigo(codigo);
		professorRepository.delete(professor);
	}
	
	public Professor alterarProfessor(Professor professor) {
		return professorRepository.save(professor);
	}

	public List<Professor> buscarTodosProfessoresPorNome(String nome) {
		
		return professorRepository.findByNomeContainingIgnoreCase(nome);
	}

}
