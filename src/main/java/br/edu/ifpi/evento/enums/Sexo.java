package br.edu.ifpi.evento.enums;

public enum Sexo {
	F				("FEMININO"),
	M				("MASCULINO");
	
	private String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
