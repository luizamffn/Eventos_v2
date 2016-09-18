package br.edu.ifpi.evento.Atividade;

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
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Notificacao;
import br.edu.ifpi.evento.modelo.Responsavel;
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
					throws DataFimMenorQueDataInicioException, AtividadeException, EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
		super(id, nome, evento, espacoFisico, hoharioInicio, hoharioTermino);
		this.valor = valor;
		this.tipoCompravel = tipo;
	}
	
	public AtividadeCompravel(AtividadeBuilder builder)
 throws DataFimMenorQueDataInicioException, AtividadeException,
			EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento{
		Validacoes.verificarDataFim(builder.horarioInicio, builder.horarioFim);
		this.horarioInicio = builder.horarioInicio;
		this.horarioTermino = builder.horarioFim;
		this.id = builder.id;
		this.nome = builder.nome;
		this.evento = builder.evento;
		this.responsavelPrincipal = builder.responsavelPrincipal;
		evento.adicionarAtividade(this);
		adicionarEspacoFisico(builder.espacoFisico);
		
	}
	
	public static class AtividadeBuilder {
		private final Long id;
		private final Calendar horarioInicio;
		private final Calendar horarioFim;
		private String nome;
		private Evento evento;
		private EspacoFisico espacoFisico;
		private Responsavel responsavelPrincipal;

		public AtividadeBuilder(Long id, Calendar horarioInicio,Calendar horarioFim, Evento evento, EspacoFisico espacoFisico) {
			this.id = id;
			this.horarioInicio = horarioInicio;
			this.horarioFim = horarioFim;
			this.evento = evento;
			this.espacoFisico = espacoFisico;
		}
		
		public AtividadeBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public AtividadeBuilder evento(Evento evento) {
			this.evento = evento;
			return this;
		}

		public AtividadeBuilder espacoFisico(EspacoFisico espacoFisico) {
			this.espacoFisico = espacoFisico;
			return this;
		}
		
		public AtividadeBuilder responsavelPrincipal(Responsavel responsavelPrincipal) {
			this.responsavelPrincipal = responsavelPrincipal;
			return this;
		}
		
		public AtividadeCompravel build()
				throws DataFimMenorQueDataInicioException, AtividadeException, EspacoFisicoComAtividadesConflitantes,
				AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
			return new AtividadeCompravel(this);
		}
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
