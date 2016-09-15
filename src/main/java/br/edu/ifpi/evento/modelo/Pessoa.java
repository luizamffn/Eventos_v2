package br.edu.ifpi.evento.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.edu.ifpi.evento.enums.Sexo;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue
	protected Long id;
	protected String nome;
	protected int cpf;
	
	@Enumerated(EnumType.STRING)
	protected Sexo sexo;
	
	public Pessoa() {
	}
	
	public Pessoa(String nome, int cpf, Sexo sexo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
	}
	public String getNome() {
		return nome;
	}
	
}