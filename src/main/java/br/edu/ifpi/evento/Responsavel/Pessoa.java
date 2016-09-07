package br.edu.ifpi.evento.Responsavel;

import br.edu.ifpi.evento.enums.Sexo;

public class Pessoa {
	protected Long id;
	protected String nome;
	protected int cpf;
	protected Sexo sexo;
	
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