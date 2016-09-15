package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.constantes.Constante;
import br.edu.ifpi.evento.cupom.Cupom;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.exceptions.PagamentoInferiorException;

@Entity
public class Inscricao {
	
	@Id
	private Long id;
	private boolean paga;
	private double valorTotal = 0;
	private double desconto = 0;
	
	@ManyToOne
	private Evento evento;
	
	@OneToOne
	private Pagamento pagamento;
	
	@ManyToMany
	@JoinTable(name = "inscricao_atividade", joinColumns = @JoinColumn(name = "inscricao_id"),
	inverseJoinColumns = @JoinColumn(name = "atividade_id"))
	private List<Atividade> atividades = new ArrayList<Atividade>();

	@ManyToOne
	private Usuario usuario;

	public Inscricao() {
	}
	
	public Inscricao(Evento evento) {
		this.evento = evento;
		this.evento.adicionarIncricao(this);
	}
	
	public Inscricao(Evento evento, Usuario usuario) throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException{
		this.evento = evento;
		this.usuario = usuario;
		this.evento.adicionarIncricao(this);
		usuario.adicionarInscricao(this);
		SeOEventoDaInscricaoForUnico();
		Notificacao.enviarNorificacao(usuario, Constante.INSCRICAO_CONCLUIDA);
	}
	
	public void SeOEventoDaInscricaoForUnico() throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		if(evento.isEventoUnico()){
			for (Atividade atividade : evento.getAtividades()) {
				if(atividade instanceof AtividadeCompravel){
					this.adicionarAtividade(atividade);
				}	
			}
		}
	}

	public void pagarInscricao(Pagamento pagamento) throws PagamentoInferiorException {
		if (pagamento.getValorRecebido() < getValorTotal()) {
			throw new PagamentoInferiorException();
		}
		paga = true;
		this.pagamento = pagamento;
		Notificacao.enviarNorificacao(usuario, Constante.INSCRICAO_PAGA);
	}

	public void adicionarAtividade(Atividade atividade)
			throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		if (paga) {
			throw new InscricaoPagaException();
		}

		verificarSeAtividadeEstaNoEvento(atividade);
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
		((AtividadeCompravel) atividade).adicionarInscricao(this);
	}

	private double calcularValorTotal() {
		for (Atividade atividade : atividades) {
			if(atividade instanceof AtividadeCompravel){
				valorTotal += ((AtividadeCompravel) atividade).getValor();
			}
		}
		return AplicarDescontoNaInscricao();
	}

	private double AplicarDescontoNaInscricao() {
		for (Cupom cupom : evento.getCupons()) {
			if (cupom.isAtivo() == true) {
				desconto += cupom.getDesconto(this);
			}
		}
		return valorTotal -= desconto;
	}

	public double getValorTotal() {
		return calcularValorTotal();
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
	
	public Usuario getUsuario() {
		return usuario;
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
