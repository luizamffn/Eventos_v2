package br.edu.ifpi.evento.enums;

public enum TipoAtividade {
	PALESTRA			("PALESTRA"),
	MINICURSO			("MINICURSO"),
	MESA_REDONDA		("MESA REDONDA"),
	INTERVALO			("INTERVALO"),
	CREDENCIAMENTO		("CREDENCIAMENTO"),
	COFFEEBREAK			("COFFEEBREAK");
	
	private String descricao;

	private TipoAtividade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
