package br.edu.ifpi.evento.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.Palestra;
import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class GeradorDeAgendas {
	
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException {
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00,00);
		Evento evento = new Evento(Long.valueOf(1), "evento1", TipoEvento.SIMPOSIO, dataInicial, dataFinal);
		
		EspacoFisico predioA = new EspacoFisico("Predio A", 1000,TipoEspacoFisico.PREDIO);
		evento.adicionarEspacoFisico(predioA);
		
		dataInicial.set(2016, 8, 12, 14, 00, 00);
		dataFinal.set(2016, 8, 20, 16, 00,00);

		EspacoFisico b3_sala4 = new EspacoFisico("B3-04", 40,TipoEspacoFisico.SALA);
		Palestra atividade = new Palestra(Long.valueOf(1), "python", evento, TipoAtividade.PALESTRA,b3_sala4,dataInicial,dataFinal, 30.00);
		dataInicial.set(2016, 8, 12, 18, 00, 00);
		dataFinal.set(2016, 8, 20, 20, 00,00);
		Palestra atividade2 = new Palestra(Long.valueOf(1), "android", evento, TipoAtividade.PALESTRA,b3_sala4,dataInicial,dataFinal, 30.00);

		EspacoFisico b3_sala10 = new EspacoFisico("B3-10", 40,TipoEspacoFisico.SALA);
		dataInicial.set(2016, 8, 12, 18, 00, 00);
		dataFinal.set(2016, 8, 20, 20, 00,00);
		Palestra atividade3 = new Palestra(Long.valueOf(1),"python", evento, TipoAtividade.PALESTRA,b3_sala10,dataInicial,dataFinal, 30.00);
		dataInicial.set(2016, 8, 12, 18, 00, 00);
		dataFinal.set(2016, 8, 20, 20, 00,00);
		Palestra atividade24 = new Palestra(Long.valueOf(1), "android", evento, TipoAtividade.PALESTRA,b3_sala10,dataInicial,dataFinal, 30.00);

//		b3_sala4.gerarAgenda();
		
		evento.gerarAgenda();
		
	}
}
