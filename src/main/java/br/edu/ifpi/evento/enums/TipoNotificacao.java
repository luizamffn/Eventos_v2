package br.edu.ifpi.evento.enums;

public enum TipoNotificacao {
	Email						("Email"),
	mensagem_de_texto			("mensagem_de_texto");
	
	private String descricao;

	private TipoNotificacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
