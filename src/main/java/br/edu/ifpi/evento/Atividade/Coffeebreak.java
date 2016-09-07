package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class Coffeebreak extends AtividadeNaoCompravel {

	public Coffeebreak(Long id, String nome, Evento evento, EspacoFisico espacoFisico, Calendar hoharioInicio,
			Calendar hoharioTermino) throws DataFimMenorQueDataInicioException, AtividadeException {
		super(id, nome, evento, espacoFisico, hoharioInicio, hoharioTermino);
	}

}
