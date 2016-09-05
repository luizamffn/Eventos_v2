package br.edu.ifpi.evento.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class Palestra extends Atividade implements Compravel{
	private double valor;
	
	public Palestra(Long id, String nome, Evento evento, TipoAtividade tipoAtividade, EspacoFisico espacoFisico,
			Calendar hoharioInicio, Calendar hoharioTermino, double valor) throws DataFimMenorQueDataInicioException {
		super(id, nome, evento, tipoAtividade, espacoFisico, hoharioInicio, hoharioTermino);
		this.valor = valor;
	}

	@Override
	public double getValor() {
		return valor;
	}

}
