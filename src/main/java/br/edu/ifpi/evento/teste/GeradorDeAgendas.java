package br.edu.ifpi.evento.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.Atividade.AtividadeNaoCompravel;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoNaoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;

public class GeradorDeAgendas {

	public static void main(String[] args)
			throws DataMenorQueAtualException, DataFimMenorQueDataInicioException, AtividadeException, EspacoFisicoComAtividadesConflitantes {
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = new EspacoFisico("Predio A", 1000, TipoEspacoFisico.PREDIO);

		Pessoa pessoa = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", pessoa, TipoUsuario.ORGANIZADOR);

		Evento evento = new Evento(Long.valueOf(1), "evento1", TipoEvento.SIMPOSIO, dataInicial, dataFinal, predioA,
				organizador, false);

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 8, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 8, 20, 16, 00, 00);

		EspacoFisico b3_sala4 = new EspacoFisico("B3-04", 40, TipoEspacoFisico.SALA);
		AtividadeCompravel atividade = new AtividadeCompravel(Long.valueOf(1), "python", evento, b3_sala4, dataInicial2,
				dataFinal2, 30.00, TipoAtividadeCompravel.PALESTRA);

		GregorianCalendar dataInicial3 = new GregorianCalendar();
		dataInicial3.set(2016, 8, 12, 18, 00, 00);
		Calendar dataFinal3 = Calendar.getInstance();
		dataFinal3.set(2016, 8, 12, 20, 00, 00);
		AtividadeCompravel atividade2 = new AtividadeCompravel(Long.valueOf(2), "android", evento, b3_sala4,
				dataInicial3, dataFinal3, 30.00, TipoAtividadeCompravel.PALESTRA);

		EspacoFisico b3_sala10 = new EspacoFisico("B3-10", 40, TipoEspacoFisico.SALA);
		GregorianCalendar dataInicial4 = new GregorianCalendar();
		dataInicial4.set(2016, 8, 12, 18, 00, 00);
		Calendar dataFinal4 = Calendar.getInstance();
		dataFinal4.set(2016, 8, 12, 20, 00, 00);
		AtividadeCompravel atividade3 = new AtividadeCompravel(Long.valueOf(3), "python", evento, b3_sala10,
				dataInicial4, dataFinal4, 30.00, TipoAtividadeCompravel.PALESTRA);

		GregorianCalendar dataInicial5 = new GregorianCalendar();
		dataInicial5.set(2016, 8, 13, 18, 00, 00);
		Calendar dataFinal5 = Calendar.getInstance();
		dataFinal5.set(2016, 8, 13, 20, 00, 00);
		AtividadeCompravel atividade24 = new AtividadeCompravel(Long.valueOf(4), "android", evento, b3_sala10,
				dataInicial5, dataFinal5, 30.00, TipoAtividadeCompravel.PALESTRA);

		EspacoFisico patio = new EspacoFisico("Patio", 200, TipoEspacoFisico.PATIO);

		GregorianCalendar dataInicial6 = new GregorianCalendar();
		dataInicial6.set(2016, 8, 13, 21, 00, 00);
		Calendar dataFinal6 = Calendar.getInstance();
		dataFinal6.set(2016, 8, 13, 22, 00, 00);
		AtividadeNaoCompravel intervalo = new AtividadeNaoCompravel(Long.valueOf(5), "intervalo", evento, patio,
				dataInicial6, dataFinal6, TipoNaoAtividadeCompravel.INTERVALO);

//		b3_sala4.gerarAgenda();

		evento.gerarAgenda();

	}
}
