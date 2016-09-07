package br.edu.ifpi.evento.modelo;

import br.edu.ifpi.evento.enums.TipoInstituicao;

public class Instituicao {
	private Long id;
	private String nome;
	private int cnpg;
	private TipoInstituicao tipoInstituicao;
	
	public Instituicao(String nome, int cnpg, TipoInstituicao tipoInstituicao) {
		super();
		this.nome = nome;
		this.cnpg = cnpg;
		this.tipoInstituicao = tipoInstituicao;
	}
	
	
}
