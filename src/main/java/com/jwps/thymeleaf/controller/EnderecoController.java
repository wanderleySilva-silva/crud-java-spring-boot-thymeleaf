package com.jwps.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jwps.thymeleaf.exception.PessoaNotFoundException;
import com.jwps.thymeleaf.model.Endereco;
import com.jwps.thymeleaf.model.Professor;
import com.jwps.thymeleaf.service.EnderecoService;
import com.jwps.thymeleaf.service.ProfessorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	ProfessorService professorService;
	
	@GetMapping("/buscar-endereco/{codigo}")
	public String novoEndereco(@PathVariable("codigo") Long codigo, Model model) {
		String pagina = "";
		try {
			Professor professor = professorService.buscarProfessorPorCodigo(codigo);
			if(professor.getEndereco() == null) {
				Endereco endereco = new Endereco();
				endereco.setProfessor(professor);
				model.addAttribute("item", endereco);
				pagina = "/novo-endereco";
			} else {
				model.addAttribute("item", professor.getEndereco());
				pagina = "/alterar-endereco";
			}
		} catch (PessoaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pagina;
	}
	
	@PostMapping("/gravar-endereco/{codigoProfessor}")
	public String gravarEndereco(@PathVariable("codigoProfessor") Long codigoProfessor,
			@ModelAttribute("item") @Valid Endereco endereco, BindingResult result, 
			RedirectAttributes attributes) {
		
		try {
			Professor professor = professorService.buscarProfessorPorCodigo(codigoProfessor);
			endereco.setProfessor(professor);
		} catch (PessoaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.hasErrors()) {
			return "/novo-endereco";
		}
		enderecoService.salvarEndereco(endereco);
		attributes.addFlashAttribute("mensagem", "Endereçoe salvo com sucesso!");
        return "redirect:/endereco/buscar-endereco/"+codigoProfessor;
	}
	
	@PostMapping("/alterar-endereco/{codigoProfessor}/{codigoEndereco}")
	public String alterarEndereco(@PathVariable("codigoProfessor") long codigoProfessor,
			@PathVariable("codigoEndereco") long codigoEndereco,
			@ModelAttribute("item") @Valid Endereco endereco, BindingResult result, 
			RedirectAttributes attributes) {
		
		try {
			Professor professor = professorService.buscarProfessorPorCodigo(codigoProfessor);
			endereco.setProfessor(professor);
			endereco.setCodigo(codigoEndereco);
		} catch (PessoaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.hasErrors()) {
			return "/alterar-endereco";
		}
		enderecoService.salvarEndereco(endereco);
		attributes.addFlashAttribute("mensagem", "Endereço  alterado com sucesso!");
        return "redirect:/endereco/buscar-endereco/"+codigoProfessor;
	}


}
