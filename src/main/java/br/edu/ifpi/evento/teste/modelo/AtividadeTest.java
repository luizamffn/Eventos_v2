package br.edu.ifpi.evento.teste.modelo;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.ResponsavelPrincipalNaoPodeSerSecudarioException;
import br.edu.ifpi.evento.exceptions.ResponsavelSecundarioNaoPodeSerRepetido;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Responsavel;
import br.edu.ifpi.evento.modelo.Usuario;

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
			EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
		responsavel = new Responsavel.ResponsavelBuilder((long) 1).build();
		Calendar horarioInicio = Calendar.getInstance();
		horarioInicio.set(2016, 10, 12, 20, 00, 00);
		Calendar horarioFim = Calendar.getInstance();
		horarioFim.set(2016, 10, 12, 22, 00, 00);
		EspacoFisico ef1 = new EspacoFisico.EspacoFisicoBuilder((long) 1).build();
		ef2 = new EspacoFisico.EspacoFisicoBuilder((long) 2).build();

		dataInicioE = Calendar.getInstance();
		dataInicioE.set(2016, 10, 10, 12, 00, 00);
		dataFimE = Calendar.getInstance();
		dataFimE.set(2016, 10, 20, 22, 00, 00);

		organizador = new Usuario();
		evento = new Evento.EventoBuilder((long) 1, dataInicioE, dataFimE, organizador).espacoFisico(ef1)
				.build();

		atividadeCompravel = new AtividadeCompravel.AtividadeBuilder((long) 1, horarioInicio, horarioFim, evento, ef2)
				.responsavelPrincipal(responsavel).build();
	}

	@Test(expected = AtividadeJaPossuiUmEvento.class)
	public void atividade_nao_pode_ser_de_mais_de_um_evento()
			throws DataMenorQueAtualException, DataFimMenorQueDataInicioException, AtividadeJaPossuiUmEvento {
		 Evento evento = new Evento.EventoBuilder((long) 2, dataInicioE, dataFimE, organizador).build();
		 atividadeCompravel.setEvento(evento);
	}
	

	@Test(expected = AtividadeComHorarioForaDoPeriodoDoEvento.class)
	public void Atividade_tem_que_esta_no_periodo_do_evento()
			throws DataFimMenorQueDataInicioException, AtividadeException, EspacoFisicoComAtividadesConflitantes,
			AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
		Calendar horarioInicio = Calendar.getInstance();
		horarioInicio.set(2016, 10, 10, 8, 00, 00);
		Calendar horarioFim = Calendar.getInstance();
		horarioFim.set(2016, 10, 10, 9, 00, 00);
		
		atividadeCompravel = new AtividadeCompravel.AtividadeBuilder((long) 2, horarioInicio, horarioFim, evento, ef2)
				.responsavelPrincipal(responsavel).build();
	}

	@Test(expected = ResponsavelPrincipalNaoPodeSerSecudarioException.class)
	public void responsavel_principal_nao_pode_ser_secundario()
			throws ResponsavelPrincipalNaoPodeSerSecudarioException, ResponsavelSecundarioNaoPodeSerRepetido {

		atividadeCompravel.adicionarResponsaveisSecudarios(responsavel);
	}

	@Test(expected = ResponsavelSecundarioNaoPodeSerRepetido.class)
	public void Atividade_nao_pode_ter_responsaveis_secundarios_repetidos()
			throws ResponsavelPrincipalNaoPodeSerSecudarioException, ResponsavelSecundarioNaoPodeSerRepetido {
		Responsavel responsavel = new Responsavel.ResponsavelBuilder((long) 2).build();

		atividadeCompravel.adicionarResponsaveisSecudarios(responsavel);
		atividadeCompravel.adicionarResponsaveisSecudarios(responsavel);
	}

}
