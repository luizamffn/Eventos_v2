package br.edu.ifpi.evento.enums;

public enum TipoEvento {
	CONGRESSO				("CONGRESSO"),
	SIMPOSIO				("SIMP�SIO"),
	SEMANA_CIENTIFICA		("SEMANA_CIENT�FICA");
	
	private String descricao;

	private TipoEvento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
