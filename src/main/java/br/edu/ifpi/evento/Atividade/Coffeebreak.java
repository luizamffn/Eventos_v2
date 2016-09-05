package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class Coffeebreak extends Atividade{

	public Coffeebreak(Long id, String nome, Evento evento, TipoAtividade tipoAtividade, EspacoFisico espacoFisico,
			Calendar hoharioInicio, Calendar hoharioTermino) throws DataFimMenorQueDataInicioException {
		super(id, nome, evento, tipoAtividade, espacoFisico, hoharioInicio, hoharioTermino);
	}

}
