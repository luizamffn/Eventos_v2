package br.edu.ifpi.evento.modelo.Atividade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.ResponsavelPrincipalNaoPodeSerSecudarioException;
import br.edu.ifpi.evento.exceptions.ResponsavelSecundarioNaoPodeSerRepetido;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.Responsavel.Responsavel;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.observer.Observable;
import br.edu.ifpi.evento.util.Notificacao;
import br.edu.ifpi.evento.util.Validacoes;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Atividade extends Observable implements Comparable<Atividade> {

	@Id
	@GeneratedValue
	protected Long id;
	protected String nome;

	@Column(insertable = false, updatable = false)
	private String tipo;

	@ManyToOne
	protected Evento evento;

	@ManyToOne
	protected EspacoFisico espacoFisico;
	protected Calendar horarioInicio;
	protected Calendar horarioTermino;

	@OneToOne
	@JoinColumn(name = "responsavel_id")
	protected Responsavel responsavelPrincipal;

	@ManyToMany
	@JoinTable(name = "atividade_responsavel", joinColumns = @JoinColumn(name = "atividade_id") , inverseJoinColumns = @JoinColumn(name = "_id") )
	protected List<Responsavel> responsaveisSecudarios = new ArrayList<Responsavel>();

	public void adicionarResponsaveisSecudarios(Responsavel responsavel)
			throws ResponsavelPrincipalNaoPodeSerSecudarioException, ResponsavelSecundarioNaoPodeSerRepetido {
		if (responsavelPrincipal.equals(responsavel)) {
			throw new ResponsavelPrincipalNaoPodeSerSecudarioException();
		}
		verificarResponsavelSecundario(responsavel);
		responsaveisSecudarios.add(responsavel);
	}

	private void verificarResponsavelSecundario(Responsavel responsavel)
			throws ResponsavelSecundarioNaoPodeSerRepetido {
		if (responsaveisSecudarios.contains(responsavel)) {
			throw new ResponsavelSecundarioNaoPodeSerRepetido();
		}
	}

	public void setEspacoFisico(EspacoFisico espacoFisico) throws EspacoFisicoComAtividadesConflitantes {
		this.espacoFisico = espacoFisico;
		this.espacoFisico.adicionarAtividade(this);
		evento.getEspacoFisico().adicionarEspacosFisicos(espacoFisico);
		if (espacoFisico != null) {
			Notificacao.notificarMudancaEspacoFisico(this);
		}
	}
	
	public void limparEvento() {
		evento = null;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Calendar getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Calendar horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	
	public Calendar getHorarioTermino() {
		return horarioTermino;
	}

	public void setHorarioTermino(Calendar horarioTermino) throws DataFimMenorQueDataInicioException {
		Validacoes.verificarDataFim(this.horarioInicio, horarioTermino);
		this.horarioTermino = horarioTermino;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setResponsavelPrincipal(Responsavel responsavelPrincipal) {
		this.responsavelPrincipal = responsavelPrincipal;
	}
	
	public List<Responsavel> getResponsaveisSecudarios() {
		return Collections.unmodifiableList(responsaveisSecudarios);
	}

	
	@Override
	public String toString() {
		return "Atividade [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", evento=" + evento + ", espacoFisico="
				+ espacoFisico + ", horarioInicio=" + horarioInicio + ", horarioTermino=" + horarioTermino
				+ ", responsavelPrincipal=" + responsavelPrincipal + ", responsaveis=" + responsaveisSecudarios + "]";
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setEvento(Evento evento)
			throws AtividadeJaPossuiUmEvento, AtividadeException, EspacoFisicoComAtividadesConflitantes,
			AtividadeHorarioForaDoPeriodoDoEvento, EventoSateliteHorarioForaDoPeriodoDoEvento {
		if (this.getEvento() != null) {
			throw new AtividadeJaPossuiUmEvento();
		}

		this.evento = evento;
		this.evento.adicionarAtividade(this);
	}

}
