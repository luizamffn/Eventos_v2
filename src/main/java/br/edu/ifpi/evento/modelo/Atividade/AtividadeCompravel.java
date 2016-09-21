package br.edu.ifpi.evento.modelo.Atividade;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.constantes.Constante;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.modelo.Notificacao;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.Responsavel.Responsavel;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;
import br.edu.ifpi.evento.util.Validacoes;

@Entity
@DiscriminatorValue(value = "At_compravel")
public class AtividadeCompravel extends Atividade {
	private Double valor;

	@Enumerated(EnumType.STRING)
	private TipoAtividadeCompravel tipoCompravel;
	
	@OneToOne
	private ItemSimples itemSimples;

	public void adicionarItem(ItemSimples itemSimples) {
		this.itemSimples = itemSimples;
	}

	public Double getValor() {
		return valor;
	}

	public TipoAtividadeCompravel getTipoCompravel() {
		return tipoCompravel;
	}

//	public void notificarMudancaEspacoFisico() {
//		for (Inscricao inscricao : itemSimples.getInscricoes()) {
//			Notificacao.enviarNorificacaoAtividade(inscricao.getUsuario(), Constante.MUDOU_ESPACO_FISICO,
//					this.getNome());
//		}
//	}

	public Item getItemSimples() {
		return itemSimples;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setTipoCompravel(TipoAtividadeCompravel tipoCompravel) {
		this.tipoCompravel = tipoCompravel;
	}
}
