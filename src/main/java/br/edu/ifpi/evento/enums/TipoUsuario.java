package br.edu.ifpi.evento.enums;

public enum TipoUsuario {
	PALESTRANTE				("PALESTRANTE"),
	PARTICIPANTE			("PARTICIPANTE"),
	ORGANIZADOR				("ORGANIZADOR"),
	MEMBROS_DA_EQUIPE		("MEMBROS_DA_EQUIPE");
	
	private String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
