package br.edu.ifpi.evento.modelo.inscricao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.constantes.Constante;
import br.edu.ifpi.evento.enums.StatusEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.EventoNaoEstaRecebendoInscricaoException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.exceptions.PagamentoInferiorException;
import br.edu.ifpi.evento.modelo.Pagamento;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Item.Item;
import br.edu.ifpi.evento.modelo.Item.ItemComposto;
import br.edu.ifpi.evento.modelo.Item.ItemSimples;
import br.edu.ifpi.evento.modelo.cupom.Cupom;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.observer.Observable;

@Entity
public class Inscricao extends Observable {

	@Id
	@GeneratedValue
	private Long id;
	private boolean paga;
	private double valorTotal = 0;
	private double desconto = 0;

	@ManyToOne
	private Evento evento;

	@OneToOne
	private Pagamento pagamento;

	@ManyToMany
	@JoinTable(name = "inscricao_item", joinColumns = @JoinColumn(name = "inscricao_id") , inverseJoinColumns = @JoinColumn(name = "item_id") )
	private List<Item> itens = new ArrayList<Item>();

	@ManyToOne
	private Usuario usuario;

	public void SeOEventoDaInscricaoForUnico() throws InscricaoPagaException, AtividadeNaoEstaNoEventoException,
			AtividadeException, EventoNaoEstaRecebendoInscricaoException {
		if (evento.isEventoUnico()) {
			for (Atividade atividade : evento.getAtividades()) {
				if (atividade instanceof AtividadeCompravel) {
					this.adicionarItem(((AtividadeCompravel) atividade).getItemSimples());
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
		setMensagem(usuario.getPessoa().getNome() + ", " + Constante.INSCRICAO_PAGA);
		notifyUmObserver(usuario);
	}

	public void adicionarItem(Item item) throws InscricaoPagaException, AtividadeNaoEstaNoEventoException,
			AtividadeException, EventoNaoEstaRecebendoInscricaoException {
		if (paga) {
			throw new InscricaoPagaException();
		}
		verificarStatusDoEvento(evento);
		verificarSeAtividadeDoItemEstaNoEvento(item);
	}

	private void verificarStatusDoEvento(Evento evento) throws EventoNaoEstaRecebendoInscricaoException {
		if (!evento.getStatus().equals(StatusEvento.RECEBENDO_INSCRICAO)) {
			throw new EventoNaoEstaRecebendoInscricaoException();
		}
	}

	private void verificarSeAtividadeDoItemEstaNoEvento(Item item)
			throws AtividadeNaoEstaNoEventoException, AtividadeException {
		if (item instanceof ItemSimples) {
			if (!evento.getAtividades().contains(((ItemSimples) item).getAtividadeCompravel())) {
				throw new AtividadeNaoEstaNoEventoException();
			}
		}

		if (item instanceof ItemComposto) {
			for (ItemSimples itemSimples : ((ItemComposto) item).getItensSimples()) {
				if (!evento.getAtividades().contains(itemSimples.getAtividadeCompravel())) {
					throw new AtividadeNaoEstaNoEventoException();
				}
			}
		}

		adicionarItemNaoRepetido(item);
	}

	private void adicionarItemNaoRepetido(Item item) throws AtividadeException {
		if (itens.contains(item)) {
			throw new AtividadeException();
		}

		itens.add(item);
		item.adicionarInscricao(this);
	}

	private double calcularValorTotal() {
		for (Item item : itens) {
			if (item instanceof ItemSimples)
				valorTotal += ((ItemSimples) item).getAtividadeCompravel().getValor();
			if (item instanceof ItemComposto)
				valorTotal += ((ItemComposto) item).getPrecoComDesconto();
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorTotal() {
		return calcularValorTotal();
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public void setEvento(Evento evento) throws InscricaoPagaException, AtividadeNaoEstaNoEventoException,
			AtividadeException, EventoNaoEstaRecebendoInscricaoException {
		// this.evento = evento;
		evento.adicionarIncricao(this);
		this.evento = evento;
		if (this.evento != null)
			SeOEventoDaInscricaoForUnico();
	}

	public Evento getEvento() {
		return evento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		if (this.usuario != null) {
			usuario.adicionarInscricao(this);
			addObserver(usuario);
			setMensagem(usuario.getPessoa().getNome() + ", " + Constante.INSCRICAO_CONCLUIDA);
			notifyUmObserver(usuario);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Item> getItens() {
		return Collections.unmodifiableList(itens);
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
