package com.jwps.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jwps.thymeleaf.exception.ProfessorNotFoundException;
import com.jwps.thymeleaf.model.AreaDeConhecimento;
import com.jwps.thymeleaf.model.Professor;
import com.jwps.thymeleaf.service.AreaDeConhecimentoService;
import com.jwps.thymeleaf.service.ProfessorService;

import jakarta.validation.Valid;

@Controller
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@Autowired
	AreaDeConhecimentoService areaDeConhecimentoService;

	@GetMapping("/")
	public String listarProfessores(Model model) {

		List<Professor> professores = professorService.listarProfessores();

		model.addAttribute("listaProfessores", professores);

		return "/lista-professores";
	}

	@PostMapping("/buscar")
	public String buscarProfessores(Model model, @Param("nome") String nome) {
		if (nome == null) {
			return "redirect:/";
		}
		List<Professor> professores = professorService.buscarTodosProfessoresPorNome(nome);
		model.addAttribute("listaProfessores", professores);
		return "/lista-professores";
	}

	@GetMapping("/novo")
	public String novoProfessor(Model model) {

		Professor professor = new Professor();

		model.addAttribute("novoProfessor", professor);

		List<AreaDeConhecimento> areasDeConhecimento = areaDeConhecimentoService.listarAreas();

		model.addAttribute("areasDeConhecimento", areasDeConhecimento);

		return "/novo-professor";
	}

	@PostMapping("/gravar")
	public String gravarProfessor(@ModelAttribute("novoProfessor") @Valid Professor professor, BindingResult erros,
			RedirectAttributes attributes, Model model) {

		if (erros.hasErrors()) {

			List<AreaDeConhecimento> areasDeConhecimento = areaDeConhecimentoService.listarAreas();

			model.addAttribute("areasDeConhecimento", areasDeConhecimento);

			return "/novo-professor";
		}

		professorService.criarProfessor(professor);

		List<Professor> professores = professorService.listarProfessores();

		attributes.addFlashAttribute("listaProfessores", professores);
		

		attributes.addFlashAttribute("mensagem", "Professor salvo com sucesso!");

		return "redirect:/";
	}

	@GetMapping("/apagar/{codigo}")
	public String excluirProfessor(@PathVariable("codigo") Long codigo, RedirectAttributes attributes) {

		try {
			professorService.excluirProfessorPorCodigo(codigo);
		} catch (ProfessorNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping("/editar/{codigo}")
	public String editarForm(@PathVariable("codigo") Long codigo, RedirectAttributes attributes, Model model) {
		try {
			Professor professor = professorService.buscarProfessorPorCodigo(codigo);
			model.addAttribute("objetoProfessor", professor);

			List<AreaDeConhecimento> areasDeConhecimento = areaDeConhecimentoService.listarAreas();

			model.addAttribute("areasDeConhecimento", areasDeConhecimento);

			return "/alterar-professor";
		} catch (ProfessorNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}

	@PostMapping("/editar/{codigo}")
	public String editarProfessor(@PathVariable("codigo") Long codigo,
			@ModelAttribute("objetoProfessor") @Valid Professor professor, BindingResult erros, Model model) {
		if (erros.hasErrors()) {

			professor.setCodigo(codigo);

			List<AreaDeConhecimento> areasDeConhecimento = areaDeConhecimentoService.listarAreas();

			model.addAttribute("areasDeConhecimento", areasDeConhecimento);

			return "/alterar-professor";
		}
		professorService.alterarProfessor(professor);
		return "redirect:/";
	}

}
