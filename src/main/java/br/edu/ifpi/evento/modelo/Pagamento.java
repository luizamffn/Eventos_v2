package br.edu.ifpi.evento.modelo;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.exceptions.PagamentoInferiorException;

public class Pagamento {
	private Long id;

	private Inscricao inscricao;
	private Calendar dataPagamento;
	private double valorRecebido;

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
