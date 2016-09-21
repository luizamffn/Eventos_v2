package br.edu.ifpi.evento.teste.modelo;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EnderecoEspacoFisicoException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EspacoFisicoPaiException;
import br.edu.ifpi.evento.modelo.Endereco;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;

public class EspacoFisicoTest {
	private EspacoFisico ef1, ef2;
	
	@Before
	public void init() {
		ef1 = EspacoFisicoBuilder.builder()
				.id((long)1)
				.getEspacoFisico();
		ef2 = EspacoFisicoBuilder.builder()
				.id((long)2)
				.getEspacoFisico();		
	}

	@Test(expected = EnderecoEspacoFisicoException.class)
	public void nao_aceita_espacos_fisicos_pais_com_mesmo_endereco() throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		Endereco endereco = new Endereco((long) 1);
		ef1.setEspacoPai(ef1);
		ef1.setEndereco(endereco);
		ef2.setEspacoPai(ef2);
		ef2.setEndereco(endereco);
	}
	
	@Test(expected = EspacoFisicoPaiException.class)
	public void somento_o_espacos_fisicos_pai_pode_ter_endereco() throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		Endereco endereco = new Endereco((long) 1);
		ef1.setEndereco(endereco);
	}
	
	@Test(expected = EspacoFisicoComAtividadesConflitantes.class)
	public void espacoFisicoNaoPodeConterAtividadesComHorariosConflitantes()
			throws DataFimMenorQueDataInicioException, EspacoFisicoComAtividadesConflitantes, AtividadeException,
			AtividadeComHorarioForaDoPeriodoDoEvento, DataMenorQueAtualException, AtividadeJaPossuiUmEvento {
		
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(2016, 10, 10, 12, 00, 00);
		Calendar dataFim = Calendar.getInstance();
		dataFim.set(2016, 10, 20, 22, 00, 00);
		Usuario organizador = new Usuario(); 
		Evento evento = EventoBuilder.builder()
				.id((long) 1)
				.dataInicio(dataInicio)
				.dataFim(dataFim)
				.organizador(organizador)
				.espacoFisico(ef1)
				.getEvento(); 
		
		evento.setEspacoFisico(EspacoFisicoBuilder.builder()
				.id((long)1)
				.getEspacoFisico());
		
		EspacoFisico espacoFisico = EspacoFisicoBuilder.builder()
				.id((long)2)
				.getEspacoFisico();
		
		Calendar dataInicialAt1 = Calendar.getInstance();
		dataInicialAt1.set(2016, 10, 12, 20, 00,00);
		Calendar dataFinalAt1 = Calendar.getInstance();
		dataFinalAt1.set(2016, 10, 12, 22, 00,00);
		
		Calendar dataInicialAt2 = Calendar.getInstance();
		dataInicialAt2.set(2016, 10, 12, 19, 00,00);
		Calendar dataFinalAt2 = Calendar.getInstance();
		dataFinalAt2.set(2016, 10, 12, 20, 00,00);

		Atividade atividade1 = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(1))
				.horarioInicio(dataInicialAt1)
				.horarioFim(dataFinalAt1)
				.evento(evento)
				.espacoFisico(ef2)
				.getAtidadeCompravel();
				
		Atividade atividade2 = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(2))
				.horarioInicio(dataInicialAt2)
				.horarioFim(dataFinalAt2)
				.evento(evento)
				.espacoFisico(ef2)
				.getAtidadeCompravel();
	}
	
	public void nao_deve_aceitar_espaco_pai_como_espaco_filho() {

	}
	
	public void nao_deve_aceitar_espaco_filho_como_espaco_pai() {

	}

}
