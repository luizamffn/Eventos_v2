package br.edu.ifpi.evento.teste.modelo;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.ResponsavelPrincipalNaoPodeSerSecudarioException;
import br.edu.ifpi.evento.exceptions.ResponsavelSecundarioNaoPodeSerRepetido;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.Responsavel.Responsavel;
import br.edu.ifpi.evento.modelo.Responsavel.ResponsavelBuilder;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;

public class AtividadeTest {
	AtividadeCompravel atividadeCompravel;
	Responsavel responsavel;
	Evento evento;
	EspacoFisico ef2;
	Calendar dataInicioE;
	Calendar dataFimE;
	Usuario organizador;

	@Before
	public void init() throws DataMenorQueAtualException, DataFimMenorQueDataInicioException, AtividadeException,
			EspacoFisicoComAtividadesConflitantes, AtividadeHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento,
			EventoSateliteHorarioForaDoPeriodoDoEvento {
		responsavel = ResponsavelBuilder.builder().id((long) 1).getResponsavel();

		Calendar horarioInicio = Calendar.getInstance();
		horarioInicio.set(2016, 10, 12, 20, 00, 00);
		Calendar horarioFim = Calendar.getInstance();
		horarioFim.set(2016, 10, 12, 22, 00, 00);
		EspacoFisico ef1 = EspacoFisicoBuilder.builder().id((long) 1).getEspacoFisico();

		ef2 = EspacoFisicoBuilder.builder().id((long) 2).getEspacoFisico();

		dataInicioE = Calendar.getInstance();
		dataInicioE.set(2016, 10, 10, 12, 00, 00);
		dataFimE = Calendar.getInstance();
		dataFimE.set(2016, 10, 20, 22, 00, 00);

		organizador = new Usuario();
		evento = EventoBuilder.builder().id((long) 1).dataInicio(dataInicioE).dataFim(dataFimE).organizador(organizador)
				.espacoFisico(ef1).getEvento();

		atividadeCompravel = AtividadeCompravelBuilder.builder().id(Long.valueOf(1)).horarioInicio(horarioInicio)
				.horarioFim(horarioFim).evento(evento).espacoFisico(ef2).responsavelPrincipal(responsavel)
				.getAtidadeCompravel();

	}

	@Test(expected = AtividadeJaPossuiUmEvento.class)
	public void atividade_nao_pode_ser_de_mais_de_um_evento()
			throws DataMenorQueAtualException, DataFimMenorQueDataInicioException, AtividadeJaPossuiUmEvento,
			AtividadeException, EspacoFisicoComAtividadesConflitantes, AtividadeHorarioForaDoPeriodoDoEvento,
			EventoSateliteHorarioForaDoPeriodoDoEvento {
		Evento evento = EventoBuilder.builder().id((long) 1).dataInicio(dataInicioE).dataFim(dataFimE)
				.organizador(organizador).getEvento();

		atividadeCompravel.setEvento(evento);
	}

	@Test(expected = AtividadeHorarioForaDoPeriodoDoEvento.class)
	public void Atividade_tem_que_esta_no_periodo_do_evento()
			throws DataFimMenorQueDataInicioException, AtividadeException, EspacoFisicoComAtividadesConflitantes,
			AtividadeHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento, EventoSateliteHorarioForaDoPeriodoDoEvento {
		Calendar horarioInicio = Calendar.getInstance();
		horarioInicio.set(2016, 10, 10, 8, 00, 00);
		Calendar horarioFim = Calendar.getInstance();
		horarioFim.set(2016, 10, 10, 9, 00, 00);

		atividadeCompravel = AtividadeCompravelBuilder.builder().id(Long.valueOf(2)).horarioInicio(horarioInicio)
				.horarioFim(horarioFim).evento(evento).espacoFisico(ef2).responsavelPrincipal(responsavel)
				.getAtidadeCompravel();

	}

	@Test(expected = ResponsavelPrincipalNaoPodeSerSecudarioException.class)
	public void responsavel_principal_nao_pode_ser_secundario()
			throws ResponsavelPrincipalNaoPodeSerSecudarioException, ResponsavelSecundarioNaoPodeSerRepetido {

		atividadeCompravel.adicionarResponsaveisSecudarios(responsavel);
	}

	@Test(expected = ResponsavelSecundarioNaoPodeSerRepetido.class)
	public void Atividade_nao_pode_ter_responsaveis_secundarios_repetidos()
			throws ResponsavelPrincipalNaoPodeSerSecudarioException, ResponsavelSecundarioNaoPodeSerRepetido {
		Responsavel responsavel = ResponsavelBuilder.builder().id((long) 2).getResponsavel();

		atividadeCompravel.adicionarResponsaveisSecudarios(responsavel);
		atividadeCompravel.adicionarResponsaveisSecudarios(responsavel);
	}

}
