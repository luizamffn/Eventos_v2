package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoNaoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class AtividadeNaoCompravel extends Atividade {

	private TipoNaoAtividadeCompravel tipo;
	
	public AtividadeNaoCompravel(Long id, String nome, Evento evento, EspacoFisico espacoFisico, Calendar hoharioInicio,
			Calendar hoharioTermino, TipoNaoAtividadeCompravel tipo) throws DataFimMenorQueDataInicioException, AtividadeException {
		super(id, nome, evento, espacoFisico, hoharioInicio, hoharioTermino);
		this.tipo = tipo;
	}

	public TipoNaoAtividadeCompravel getTipo() {
		return tipo;
	}

	
}
