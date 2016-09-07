package br.edu.ifpi.evento.teste;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoInstituicao;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Instituicao;

public class EventoTest {

	Calendar dataInicial;
	Calendar dataFinal;
	Evento evento;
	Inscricao inscricao;

	@Before
	public void init() throws DataMenorQueAtualException, DataFimMenorQueDataInicioException{
		dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 20, 44, 11);
		dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 15, 20, 44, 11);
		evento = new Evento(Long.valueOf(1), "Evento1", TipoEvento.CONGRESSO, dataInicial, dataFinal);
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_eventos_data_passada() throws Exception {
		dataInicial.set(2016, 5, 12, 20, 44, 11);
		dataFinal.set(2016, 8, 12, 22, 00);
		evento = new Evento((long) 1,"teste", TipoEvento.SEMANA_CIENTIFICA, dataInicial, dataFinal);
	}
	
	@Test
	public void deve_settar_automaticamente_em_inscrição_este_evento() throws Exception {
		dataInicial.set(2016, 9, 12, 20, 44);
		dataFinal.set(2016, 12, 12, 22, 00);
		evento = new Evento((long) 2,"teste", TipoEvento.SIMPOSIO, dataInicial, dataFinal);
		inscricao = new Inscricao(evento);
		assertEquals(inscricao.getEvento().equals(evento), true);
	}
	
	@Test
	public void evento_recem_criado_deve_ter_zero_atividades() throws Exception {
		dataInicial.set(2016, 9, 12, 20, 44, 11);
		dataFinal.set(2016, 12, 12, 22, 00);
		evento = new Evento((long) 3,"teste", TipoEvento.SEMANA_CIENTIFICA, dataInicial, dataFinal);
		assertEquals(0, evento.getAtividades().size());
	}
	
//	@Test
//	public void deve_criar_UmEvento_ComNome_EDescricao_NaoPublicado() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void deve_aceitar_eventos_com_data_hoje_ou_futura() throws Exception{
		dataInicial.set(2016, 9, 18, 20, 44);
		dataFinal.set(2016, 12, 12, 22, 00);
		evento = new Evento((long) 4,"teste", TipoEvento.CONGRESSO, dataInicial, dataFinal);
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_eventos_data_fim_menor_que_data_inicio() throws Exception {
		dataInicial.set(2016, 9, 12, 20, 44, 11);
		dataFinal.set(2016, 4, 12, 22, 00);
		evento = new Evento((long) 1,"teste", TipoEvento.SEMANA_CIENTIFICA, dataInicial, dataFinal);
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_instituicoes_repetidas() throws Exception {
		Instituicao instituicao = new Instituicao("IFPI", 99998877, TipoInstituicao.PUBLICA);
		evento.adicionarInstituicao(instituicao);
		evento.adicionarInstituicao(instituicao);
	}
	
}
