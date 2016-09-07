package br.edu.ifpi.evento.enums;

public enum TipoAtividadeCompravel {
	PALESTRA				("PALESTRA"),
	MINICURSO				("MINICURSO"),
	MESA_REDONDA			("MESA REDONDA");
	
	private String descricao;

	private TipoAtividadeCompravel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
