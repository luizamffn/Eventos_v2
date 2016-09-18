package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.constantes.Constante;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Notificacao;
import br.edu.ifpi.evento.util.Validacoes;

@Entity
@DiscriminatorValue(value = "At_compravel")
public class AtividadeCompravel extends Atividade {
	private Double valor;

//	@ManyToMany(mappedBy = "atividades")

	@Enumerated(EnumType.STRING)
	private TipoAtividadeCompravel tipoCompravel;
	
	@OneToOne
	private ItemSimples itemSimples;

	public AtividadeCompravel() {
	}

	public AtividadeCompravel(Long id, String nome, Evento evento, EspacoFisico espacoFisico, Calendar hoharioInicio,
			Calendar hoharioTermino, Double valor, TipoAtividadeCompravel tipo)
					throws DataFimMenorQueDataInicioException, AtividadeException, EspacoFisicoComAtividadesConflitantes {
		super(id, nome, evento, espacoFisico, hoharioInicio, hoharioTermino);
		this.valor = valor;
		this.tipoCompravel = tipo;
	}
	
	public AtividadeCompravel(Long id, EspacoFisico espacoFisico, Calendar hoharioInicio, Calendar hoharioTermino)
			throws DataFimMenorQueDataInicioException, EspacoFisicoComAtividadesConflitantes {
		super(id, espacoFisico, hoharioInicio, hoharioTermino);
	}

	public void adicionarItem(ItemSimples itemSimples) {
		this.itemSimples = itemSimples;
	}

	public Double getValor() {
		return valor;
	}

	public TipoAtividadeCompravel getTipoCompravel() {
		return tipoCompravel;
	}

	public void notificarMudancaEspacoFisico() {
		for (Inscricao inscricao : itemSimples.getInscricoes()) {
			Notificacao.enviarNorificacaoAtividade(inscricao.getUsuario(), Constante.MUDOU_ESPACO_FISICO,
					this.getNome());
		}
	}

	public Item getItemSimples() {
		return itemSimples;
	}
}
