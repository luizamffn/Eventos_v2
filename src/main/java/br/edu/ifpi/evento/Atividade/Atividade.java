package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Notificacao;
import br.edu.ifpi.evento.modelo.Responsavel;
import br.edu.ifpi.evento.util.Validacoes;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Atividade {

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
	protected Calendar hoharioInicio;
	protected Calendar hoharioTermino;

	@ManyToOne
	@JoinColumn(name = "atividadePai_id")
	protected Responsavel responsavelPrincipal;

	@OneToMany
	protected List<Responsavel> responsaveis;

	public Atividade() {
	}

	public Atividade(Long id, String nome, Evento evento, EspacoFisico espacoFisico, Calendar hoharioInicio,
			Calendar hoharioTermino) throws DataFimMenorQueDataInicioException, AtividadeException {
		Validacoes.verificarDataFim(hoharioInicio, hoharioTermino);
		this.id = id;
		this.nome = nome;
		this.evento = evento;
		evento.adicionarAtividade(this);
		adicionarEspacoFisico(espacoFisico);
		this.hoharioInicio = hoharioInicio;
		this.hoharioTermino = hoharioTermino;
	}

	private void adicionarEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
		espacoFisico.adicionarAtividade(this);
		evento.getEspacoFisico().adicionarEspacosFisicos(espacoFisico);
	}

	public String getNome() {
		return nome;
	}

	public Calendar getHoharioInicio() {
		return hoharioInicio;
	}

	public Calendar getHoharioTermino() {
		return hoharioTermino;
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

	public void setEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
		Notificacao.notificarMudancaEspacoFisico(this);
	}

}
