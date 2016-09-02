package br.edu.ifpi.evento.modelo;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.util.Validacoes;

public class Atividade {
	private Long id;
	private double valor = 0;
	private String nome;
	private Evento evento;
	private TipoAtividade tipoAtividade;
	private EspacoFisico espacoFisico;
	private Calendar hoharioInicio;
	private Calendar hoharioTermino;
	
	public Atividade(Long id, double valor, String nome, Evento evento, TipoAtividade tipoAtividade) {
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.tipoAtividade = tipoAtividade;
	}
	
	public Atividade(Long id, double valor, String nome, Evento evento, TipoAtividade tipoAtividade,
			EspacoFisico espacoFisico, Calendar hoharioInicio, Calendar hoharioTermino) throws DataFimMenorQueDataInicioException {
		Validacoes.verificarDataFim(hoharioInicio, hoharioTermino);
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.evento = evento;
		adicionarEspacoFisico(espacoFisico);
		this.tipoAtividade = tipoAtividade;
		this.hoharioInicio = hoharioInicio;
		this.hoharioTermino = hoharioTermino;
	}

	public void adicionarEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
		espacoFisico.adicionarAtividade(this);
		evento.getEspacoFisico().adicionarEspacosFisicos(espacoFisico);
	}
	
	
	public double getValor() {
		return valor;
	}
	
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
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
