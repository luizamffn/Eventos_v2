package br.edu.ifpi.evento.util;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.CupomForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;

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
					throws EspacoFisicoComAtividadesConflitantes, AtividadeHorarioForaDoPeriodoDoEvento {
		if (verificarDatas(dataInicio1, dataFim1, dataInicio2, dataFim2) == true) {
			throw new AtividadeHorarioForaDoPeriodoDoEvento();
		}
	}
	
	public static void verificarHorariosDoEventoSatelite(Calendar dataInicio1, Calendar dataFim1,
			Calendar dataInicio2, Calendar dataFim2) throws EventoSateliteHorarioForaDoPeriodoDoEvento{
		if (verificarDatas(dataInicio1, dataFim1, dataInicio2, dataFim2) == true) {
			throw new EventoSateliteHorarioForaDoPeriodoDoEvento();
		}
	}
	
	private static boolean verificarDatas(Calendar dataInicio1, Calendar dataFim1,
			Calendar dataInicio2, Calendar dataFim2){
		if (!(dataInicio1.getTimeInMillis() <= dataInicio2.getTimeInMillis()
				&& dataFim1.getTimeInMillis() >= dataInicio2.getTimeInMillis()
				|| dataInicio1.getTimeInMillis() <= dataFim2.getTimeInMillis()
						&& dataFim1.getTimeInMillis() >= dataFim2.getTimeInMillis())) {
			return true;
		}
		return false;
	}

	public static void verificarHorariosDoCumpomEveto(Calendar dataInicioEvento, Calendar dataFimEvento,
			Calendar dataValidade) throws CupomForaDoPeriodoDoEvento{
		if (!(dataInicioEvento.getTimeInMillis() <= dataValidade.getTimeInMillis()
				&& dataFimEvento.getTimeInMillis() >= dataValidade.getTimeInMillis())) {
			throw new CupomForaDoPeriodoDoEvento();
		}
	}
}
