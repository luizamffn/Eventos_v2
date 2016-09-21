package br.edu.ifpi.evento.enums;

public enum StatusEvento {
	CADASTRADO			      	("CADASTRADO"),
	RECEBENDO_INSCRICAO			("RECEBENDO INSCRIÇÃO"),
	EM_ANDAMENTO				("EM_ANDAMENTO"),
	FINALIZADO					("FINALIZADO"),
	CANCELADO					("CANCELADO");
	
	private String descricao;

	private StatusEvento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
