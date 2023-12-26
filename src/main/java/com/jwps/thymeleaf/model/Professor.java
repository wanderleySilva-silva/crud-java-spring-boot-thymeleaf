package com.jwps.thymeleaf.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "O nome deve ser informado")
	@Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
	private String nome;

	@Min(value = 18, message = "O professor deve ter no mínimo 18 anos")
	private int idade;
	
	@NotBlank(message = "O CPF deve ser informado")
	private String cpf;
	
	@OneToOne(mappedBy = "professor", cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@ManyToOne
	private AreaDeConhecimento areaDeConhecimento;
	
	
}
