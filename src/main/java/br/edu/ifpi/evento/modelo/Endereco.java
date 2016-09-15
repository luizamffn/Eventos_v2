package br.edu.ifpi.evento.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue
	private Long id;
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	
	
}
