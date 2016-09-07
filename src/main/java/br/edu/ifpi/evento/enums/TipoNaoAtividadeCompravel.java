package br.edu.ifpi.evento.enums;

public enum TipoNaoAtividadeCompravel {
	INTERVALO				("INTERVALO"),
	CREDENCIAMENTO			("CREDENCIAMENTO"),
	COFFEEBREAK				("COFFEEBREAK");
	
	private String descricao;

	private TipoNaoAtividadeCompravel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
