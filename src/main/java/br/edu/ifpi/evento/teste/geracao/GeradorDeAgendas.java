package br.edu.ifpi.evento.teste.geracao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeNaoCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeNaoCompravelBuilder;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;

public class GeradorDeAgendas {

	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException,
			AtividadeException, EspacoFisicoComAtividadesConflitantes, AtividadeHorarioForaDoPeriodoDoEvento,
			AtividadeJaPossuiUmEvento, EventoSateliteHorarioForaDoPeriodoDoEvento {
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = EspacoFisicoBuilder.builder().id((long) 1).descricao("PredioA").getEspacoFisico();

		Pessoa pessoa = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", pessoa, TipoUsuario.ORGANIZADOR);

		Evento evento = EventoBuilder.builder().id((long) 1).dataInicio(dataInicial).dataFim(dataFinal)
				.organizador(organizador).nome("Evento1").espacoFisico(predioA).getEvento();

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 10, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 10, 12, 16, 00, 00);

		EspacoFisico b3_sala4 = EspacoFisicoBuilder.builder().id((long) 2).descricao("B3-04").getEspacoFisico();
		AtividadeCompravel atividade = AtividadeCompravelBuilder.builder().id(Long.valueOf(1)).nome("python")
				.horarioInicio(dataInicial2).horarioFim(dataFinal2).evento(evento).espacoFisico(b3_sala4)
				.getAtidadeCompravel();

		// new AtividadeCompravel(Long.valueOf(1), "python", evento, b3_sala4,
		// dataInicial2,
		// dataFinal2, 30.00, TipoAtividadeCompravel.PALESTRA);

		GregorianCalendar dataInicial3 = new GregorianCalendar();
		dataInicial3.set(2016, 10, 12, 18, 00, 00);
		Calendar dataFinal3 = Calendar.getInstance();
		dataFinal3.set(2016, 10, 12, 20, 00, 00);
		AtividadeCompravel atividade2 = AtividadeCompravelBuilder.builder().id(Long.valueOf(2)).nome("android")
				.horarioInicio(dataInicial3).horarioFim(dataFinal3).evento(evento).espacoFisico(b3_sala4)
				.getAtidadeCompravel();

		// new AtividadeCompravel(Long.valueOf(2), "android", evento, b3_sala4,
		// dataInicial3, dataFinal3, 30.00, TipoAtividadeCompravel.PALESTRA);

		EspacoFisico b3_sala10 = EspacoFisicoBuilder.builder().id((long) 3).descricao("B3-10").getEspacoFisico();

		GregorianCalendar dataInicial4 = new GregorianCalendar();
		dataInicial4.set(2016, 10, 12, 21, 00, 00);
		Calendar dataFinal4 = Calendar.getInstance();
		dataFinal4.set(2016, 10, 12, 22, 00, 00);
		AtividadeCompravel atividade3 = AtividadeCompravelBuilder.builder().id(Long.valueOf(3)).nome("python")
				.horarioInicio(dataInicial4).horarioFim(dataFinal4).evento(evento).espacoFisico(b3_sala10)
				.getAtidadeCompravel();

		GregorianCalendar dataInicial5 = new GregorianCalendar();
		dataInicial5.set(2016, 10, 12, 14, 00, 00);
		Calendar dataFinal5 = Calendar.getInstance();
		dataFinal5.set(2016, 10, 12, 17, 00, 00);
		AtividadeCompravel atividade24 = AtividadeCompravelBuilder.builder().id(Long.valueOf(4)).nome("android")
				.horarioInicio(dataInicial5).horarioFim(dataFinal5).evento(evento).espacoFisico(b3_sala10)
				.getAtidadeCompravel();

		EspacoFisico patio = EspacoFisicoBuilder.builder().id((long) 4).descricao("Patio").getEspacoFisico();

		GregorianCalendar dataInicial6 = new GregorianCalendar();
		dataInicial6.set(2016, 10, 13, 21, 00, 00);
		Calendar dataFinal6 = Calendar.getInstance();
		dataFinal6.set(2016, 10, 13, 22, 00, 00);
		AtividadeNaoCompravel intervalo = AtividadeNaoCompravelBuilder.builder().id(Long.valueOf(6)).nome("intervalo")
				.horarioInicio(dataInicial6).horarioFim(dataFinal6).evento(evento).espacoFisico(patio)
				.getAtividadeNaoCompravel();

//		b3_sala4.gerarAgenda();

		evento.gerarAgenda();

	}
}
