package br.edu.ifpi.evento.cupom;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.modelo.Inscricao;

public abstract class Cupom {
	private String codigo;
	private boolean ativo;
	private Calendar validade;
	
	public Cupom(boolean ativo, Calendar validade) {
		this.ativo = ativo;
		this.validade = validade;
	}

	public abstract double getDesconto(Inscricao inscricao);
	
	public boolean isAtivo() {
		Calendar now = new GregorianCalendar();
		return validade.getTimeInMillis() > now.getTimeInMillis();
	}
}
