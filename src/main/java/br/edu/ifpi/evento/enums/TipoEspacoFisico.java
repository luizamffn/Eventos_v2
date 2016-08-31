package br.edu.ifpi.evento.enums;

public enum TipoEspacoFisico {
	PREDIO				("PR�DIO"),
	SALA				("SALA"),
	AUDITORIO			("AUDIT�RIO"),
	LABORATORIO			("LABORAT�RIO");
	
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
