package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class Palestra extends AtividadeCompravel{

	public Palestra(Long id, String nome, Evento evento, EspacoFisico espacoFisico, Calendar hoharioInicio,
			Calendar hoharioTermino, Double valor) throws DataFimMenorQueDataInicioException, AtividadeException {
		super(id, nome, evento, espacoFisico, hoharioInicio, hoharioTermino, valor);
	}

	
}
