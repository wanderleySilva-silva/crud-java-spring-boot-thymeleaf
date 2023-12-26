package com.jwps.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jwps.thymeleaf.model.AreaDeConhecimento;
import com.jwps.thymeleaf.model.Professor;
import com.jwps.thymeleaf.service.AreaDeConhecimentoService;
import com.jwps.thymeleaf.service.ProfessorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/areaDeConhecimento")
public class AreaDeConhecimentoController {
	
	@Autowired
	private AreaDeConhecimentoService areaDeConhecimentoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/novo")
	public String novaAreaDeConhecimento(Model model) {

		AreaDeConhecimento areaDeConhecimento = new AreaDeConhecimento();

		model.addAttribute("novoAreaDeConhecimento", areaDeConhecimento);

		return "/novo-areaDeConhecimento";
	}
	
	@PostMapping("/gravar")
	public String gravarAreaDeConhecimento(@ModelAttribute("novoAreaDeConhecimento") @Valid AreaDeConhecimento areaDeConhecimento, BindingResult erros,
			RedirectAttributes attributes) {

		if (erros.hasErrors()) {
			return "/novo-areaDeConhecimento";
		}
		
		areaDeConhecimentoService.gravar(areaDeConhecimento);
		
		List<Professor> professores = professorService.listarProfessores();
		
		attributes.addFlashAttribute("listaProfessores", professores);

		attributes.addFlashAttribute("mensagem", "Área de conhecimento salva com sucesso!");

		return "redirect:/";
	}

}
