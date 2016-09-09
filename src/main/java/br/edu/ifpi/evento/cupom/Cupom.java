package br.edu.ifpi.evento.cupom;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;

public abstract class Cupom {
	private Long id;
	private String codigo;
	private Calendar validade;
	
	private Evento evento;

	public Cupom(String codigo, Calendar validade) {
		this.codigo = codigo;
		this.validade = validade;
	}

	public abstract double getDesconto(Inscricao inscricao);

	public boolean isAtivo() {
		Calendar now = new GregorianCalendar();
		return validade.getTimeInMillis() > now.getTimeInMillis();
	}
}
