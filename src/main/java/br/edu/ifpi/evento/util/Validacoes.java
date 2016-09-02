package br.edu.ifpi.evento.util;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;

public class Validacoes {
	
	public static void verificarDataFim(Calendar dataInicio, Calendar dataFim) throws DataFimMenorQueDataInicioException{
		if (dataFim.getTimeInMillis()- dataInicio.getTimeInMillis() <0 ) {
			throw new DataFimMenorQueDataInicioException();
		}
	}
}
