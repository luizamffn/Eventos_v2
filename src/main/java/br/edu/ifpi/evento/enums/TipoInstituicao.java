package br.edu.ifpi.evento.enums;

public enum TipoInstituicao {
	PUBLICA				("PÚBLICA"),
	PRIVADA				("PRIVADA");
	
	private String descricao;

	private TipoInstituicao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
