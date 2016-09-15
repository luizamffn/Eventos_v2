package br.edu.ifpi.evento.cupom;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Cupom {
	
	@Id
	@GeneratedValue
	private Long id;
	private String codigo;
	private Calendar validade;
	
	@ManyToOne
	private Evento evento;
	
	public Cupom() {
	}

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
