package com.jwps.thymeleaf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "O logradouro deve ser informado")
	@Size(min = 2, message = "O logradouro deve ter no mínimo 2 caracteres")
	private String logradouro;
	
	@NotBlank(message = "O número deve ser informado")
	@Size(min = 2, message = "O número deve ter no mínimo 2 caracteres")
	private String numero;
	
	@NotBlank(message = "O cep deve ser informado")
	@Size(min = 8, message = "O cep deve ter no mínimo 2 caracteres")
	private String cep;
	
	@OneToOne
	private Professor professor;


}
