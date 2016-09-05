package br.edu.ifpi.evento.enums;

public enum TipoEvento {
	CONGRESSO				("CONGRESSO"),
	SIMPOSIO				("SIMPÓSIO"),
	SEMANA_CIENTIFICA		("SEMANA_CIENTÍFICA");
	
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
