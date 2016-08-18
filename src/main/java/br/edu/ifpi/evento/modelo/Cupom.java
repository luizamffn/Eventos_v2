package br.edu.ifpi.evento.modelo;

public class Cupom {
	private String codigo;
	private double porcentagemDoDesconto;
	private boolean ativo;
	
	
	public Cupom(String codigo, double porcentagemDoDesconto, boolean ativo) {
		super();
		this.codigo = codigo;
		this.porcentagemDoDesconto = porcentagemDoDesconto;
		this.ativo = ativo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getPorcentagemDoDesconto() {
		return porcentagemDoDesconto;
	}
	public void setPorcentagemDoDesconto(double porcentagemDoDesconto) {
		this.porcentagemDoDesconto = porcentagemDoDesconto;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
