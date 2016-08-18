package br.edu.ifpi.evento.modelo;

import br.edu.ifpi.evento.enums.TipoInstituicao;

public class Instituicao {
	private Long id;
	private String nome;
	private int cnpg;
	private TipoInstituicao tipoInstituicao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
