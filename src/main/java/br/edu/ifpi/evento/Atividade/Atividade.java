package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;
import java.util.List;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Responsavel;
import br.edu.ifpi.evento.util.Validacoes;

public abstract class Atividade {
	protected Long id;
	protected String nome;
	protected Evento evento;
	protected EspacoFisico espacoFisico;
	protected Calendar hoharioInicio;
	protected Calendar hoharioTermino;
	
	protected Responsavel responsavelPrincipal;
	protected List<Responsavel> responsaveis;

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

}
