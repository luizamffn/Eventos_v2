package br.edu.ifpi.evento.enums;

public enum TipoEspacoFisico {
	PREDIO				("PRÉDIO"),
	SALA				("SALA"),
	AUDITORIO			("AUDITÓRIO"),
	LABORATORIO			("LABORATÓRIO");
	
	private String descricao;

	private TipoEspacoFisico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
