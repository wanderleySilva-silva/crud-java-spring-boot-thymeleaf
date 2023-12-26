package com.jwps.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwps.thymeleaf.model.AreaDeConhecimento;
import com.jwps.thymeleaf.repository.AreaDeConhecimentoRepository;

@Service
public class AreaDeConhecimentoService {

	@Autowired
	private AreaDeConhecimentoRepository areaDeConhecimentoRepository;

	public AreaDeConhecimento gravar(AreaDeConhecimento areaDeConhecimento) {

		return areaDeConhecimentoRepository.save(areaDeConhecimento);

	}
	
	public List<AreaDeConhecimento> listarAreas(){
		
		return areaDeConhecimentoRepository.findAll();
	}

}
