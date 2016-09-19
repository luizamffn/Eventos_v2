package br.edu.ifpi.evento.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.exceptions.PagamentoInferiorException;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;

@Entity
public class Pagamento {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Inscricao inscricao;
	private Calendar dataPagamento;
	private double valorRecebido;
	
	public Pagamento() {
	}

	public Pagamento(Inscricao inscricao,double valorRecebido)
			throws PagamentoInferiorException, InscricaoPagaException {
		if (inscricao.isPaga() == true) throw new InscricaoPagaException();
		
		this.inscricao = inscricao;
		this.valorRecebido = valorRecebido;

		inscricao.pagarInscricao(this);
		dataPagamento = Calendar.getInstance();
	}

	public double getValorRecebido() {
		return valorRecebido;
	}
}
