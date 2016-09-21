package br.edu.ifpi.evento.util;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.CupomForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;

public class Validacoes {

	public static void verificarDataFim(Calendar dataInicio, Calendar dataFim)
			throws DataFimMenorQueDataInicioException {
		if (dataFim.getTimeInMillis() - dataInicio.getTimeInMillis() < 0) {
			throw new DataFimMenorQueDataInicioException();
		}
	}

	public static void verificarHorariosAtividades(Calendar dataInicio1, Calendar dataFim1, Calendar dataInicio2,
			Calendar dataFim2) throws EspacoFisicoComAtividadesConflitantes {
		if (dataInicio1.getTimeInMillis() - dataInicio2.getTimeInMillis() < 0
				&& dataFim1.getTimeInMillis() - dataInicio2.getTimeInMillis() > 0
				|| dataInicio1.getTimeInMillis() <= dataFim2.getTimeInMillis()
						&& dataFim1.getTimeInMillis() >= dataFim2.getTimeInMillis()) {
			throw new EspacoFisicoComAtividadesConflitantes();
		}
	}

	public static void verificarHorariosAtividadesDoEvento(Calendar dataInicio1, Calendar dataFim1,
			Calendar dataInicio2, Calendar dataFim2)
					throws EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento {
		if (!(dataInicio1.getTimeInMillis() <= dataInicio2.getTimeInMillis()
				&& dataFim1.getTimeInMillis() >= dataInicio2.getTimeInMillis()
				|| dataInicio1.getTimeInMillis() <= dataFim2.getTimeInMillis()
						&& dataFim1.getTimeInMillis() >= dataFim2.getTimeInMillis())) {
			throw new AtividadeComHorarioForaDoPeriodoDoEvento();
		}
	}

	public static void verificarHorariosDoCumpomEveto(Calendar dataInicioEvento, Calendar dataFimEvento,
			Calendar dataValidade) throws CupomForaDoPeriodoDoEvento{
		if (!(dataInicioEvento.getTimeInMillis() <= dataValidade.getTimeInMillis()
				&& dataFimEvento.getTimeInMillis() >= dataValidade.getTimeInMillis())) {
			throw new CupomForaDoPeriodoDoEvento();
		}
	}
}
