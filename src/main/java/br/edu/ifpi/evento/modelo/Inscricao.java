package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.CupomException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.exceptions.PagamentoInferiorException;

public class Inscricao {
	private Long id;
	private Calendar dataPagamento;
	private Pagamento pagamento;
	private boolean paga;
	private double valorTotal = 0;
	private Evento evento;
	private double desconto = 0;
	private List<Cupom> cupons = new ArrayList<>();

	private List<Atividade> atividades = new ArrayList<>();

	public Inscricao(Evento evento) {
		this.evento = evento;
		this.evento.adicionarIncricao(this);
	}

	public void pagarInscricao(Pagamento pagamento) throws PagamentoInferiorException {
		if (pagamento.getValorRecebido() < valorTotal) {
			throw new PagamentoInferiorException();
		}
		paga = true;
		this.pagamento = pagamento; 
	}

	public void adicionarAtividade(Atividade atividade)
			throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		if (paga) {
			throw new InscricaoPagaException();
		}

		verificarSeAtividadeEstaNoEvento(atividade);
		calcularValorTotal();
	}

	private void verificarSeAtividadeEstaNoEvento(Atividade atividade)
			throws AtividadeNaoEstaNoEventoException, AtividadeException {
		if (!evento.getAtividades().contains(atividade)) {
			throw new AtividadeNaoEstaNoEventoException();
		}

		adicionarAtividadeNaoRepetida(atividade);
	}

	private void adicionarAtividadeNaoRepetida(Atividade atividade) throws AtividadeException {
		if (atividades.contains(atividade)) {
			throw new AtividadeException();
		}

		atividades.add(atividade);
	}

	private double calcularValorTotal() {
		for (Atividade atividade : atividades) {
			valorTotal += atividade.getValor();
		}
		return AplicarDescontoNaInscricao();
	}

	private double AplicarDescontoNaInscricao() {
		for (Cupom cupom : cupons) {
			if (cupom.isAtivo()) {
				desconto += valorTotal * cupom.getPorcentagemDoDesconto();
			}
		}
		return valorTotal -= desconto;
	}

	public void adicionarCupom(Cupom cupom) throws CupomException {
		if (cupons.contains(cupom)) {
			throw new CupomException();

		}
		cupons.add(cupom);
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public boolean isPaga() {
		return paga;
	}

	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}

	public Evento getEvento() {
		return evento;
	}

	public List<Cupom> getCupons() {
		return Collections.unmodifiableList(cupons);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inscricao other = (Inscricao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
