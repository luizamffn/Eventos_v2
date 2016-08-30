package br.edu.ifpi.evento.modelo;

import java.util.Date;

public class Cupom {
	private String codigo;
	private double porcentagemDoDesconto;
	private boolean ativo;
	private Date validade;
	
	public Cupom(String codigo, double porcentagemDoDesconto, boolean ativo) {
		super();
		this.codigo = codigo;
		this.porcentagemDoDesconto = porcentagemDoDesconto;
		this.ativo = ativo;
	}
	
	public double getPorcentagemDoDesconto() {
		return porcentagemDoDesconto;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

}
