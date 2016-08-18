package br.edu.ifpi.evento.enums;

public enum StatusEvento {
	CADASTRADO			      	("CADASTRADO"),
	RECEBENDO_INSCRICAO			("RECEBENDO INSCRIÇÃO"),
	FINALIZADO					("FINALIZADO");
	
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
