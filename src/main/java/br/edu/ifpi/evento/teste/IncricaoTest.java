package br.edu.ifpi.evento.teste;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.Atividade.Palestra;
import br.edu.ifpi.evento.Responsavel.Pessoa;
import br.edu.ifpi.evento.cupom.Palestras_50;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.CupomException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Pagamento;
import br.edu.ifpi.evento.modelo.Usuario;

public class IncricaoTest {
	Calendar dataInicial;
	Calendar dataFinal;
	Evento evento;
	Inscricao inscricao;
	Calendar validadePalestra = Calendar.getInstance();
	Pagamento pagamento;
	EspacoFisico espacoFisico;
	Usuario organizador;

	@Before
	public void init() throws Exception{
		dataInicial = new GregorianCalendar();
		dataFinal = Calendar.getInstance();
		dataInicial.set(2016, 9, 12, 20, 44);
		dataFinal.set(2016, 12, 12, 22, 00);
		validadePalestra.set(2016, 9, 12, 20, 44);
		
		Pessoa pessoa = new Pessoa("Josefa", 4454, Sexo.F);
		organizador = new Usuario("Jose123", "8766Y", pessoa, TipoUsuario.ORGANIZADOR);
		
		espacoFisico = new EspacoFisico("Instituto Federal", 1000, TipoEspacoFisico.PREDIO);
		evento = new Evento((long) 1,"teste1", TipoEvento.CONGRESSO, dataInicial, dataFinal, espacoFisico,organizador);
		
		espacoFisico = new EspacoFisico("Predi A", 300, TipoEspacoFisico.PREDIO);
		Palestra atividade = new Palestra(Long.valueOf(1), "java pra web", evento , espacoFisico, dataInicial, dataFinal, 20.00);
		
		inscricao = new Inscricao(evento);
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
		
		Palestras_50 palestras_50 = new Palestras_50("p50", validadePalestra);
		evento.adicionarCupons(palestras_50);
		
//		evento.gerarAgenda();
	}
	
	@Test
	public void nao_deve_aplicar_descontos_de_cupons_nao_ativos() throws Exception {
		Palestra atividade = new Palestra(Long.valueOf(2), "html", evento, espacoFisico, dataInicial, dataFinal, 40.00);
		inscricao.adicionarAtividade(atividade);
		System.out.println(inscricao.getValorTotal());
	}
	
	@Test
	public void valor_da_inscricao_eh_o_total_dos_seus_itens() {
		System.out.println(inscricao.getValorTotal());
		assertEquals(10.0, inscricao.getValorTotal(),0.0);
	}
	
	@Test
	public void deve_marcar_inscricao_como_paga_aoo_receber_pagamento() throws Exception {
		pagamento = new Pagamento(inscricao, 20.0);
		inscricao.pagarInscricao(pagamento);
		assertEquals(true, inscricao.isPaga());
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_icluir_atividades_repetidas() throws Exception {
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
	}
	
	@Test
	public void inscricao_recem_criada_deve_ter_zero_atividades() {
		Inscricao inscricao2 = new Inscricao(evento);
		int tamanho  = inscricao2.getAtividades().size();
		assertEquals(0, tamanho);

	}
	
	@Test(expected = Exception.class)
	public void inscricoes_com_pagamentos_inferiores_ao_valor_a_pagar_devem_ser_invalidos() throws Exception {
		pagamento = new Pagamento(inscricao, 9.0);
		inscricao.pagarInscricao(pagamento);
	}
	
	@Test
	public void deve_aceitar_incluir_atividades_que_estejam_no_seu_evento() throws Exception {
		Palestra palestra = new Palestra(Long.valueOf(2), "algoritmos", evento, espacoFisico, dataInicial, dataFinal, 50.00);

		inscricao.adicionarAtividade(palestra);
	}
	
	@Test
	public void inscricao_sem_itens_deve_ter_valor_zero() {
		Inscricao inscricao3 = new Inscricao(evento);
		assertEquals(0.0, inscricao3.getValorTotal(),0.0);
	}
	
	@Test
	public void inscricao_deve_aplicar_descontos_ativos_no_evento() throws CupomException {
		inscricao.getValorTotal();
	}
	
	@Test(expected = Exception.class)
	public void nao_deve_aceitar_incluir_atividades_de_outros_eventos() throws Exception {
		EspacoFisico predioA = new EspacoFisico("Predio A", 1000,TipoEspacoFisico.PREDIO);

		Evento evento2 = new Evento(Long.valueOf(2), "evento2", TipoEvento.SEMANA_CIENTIFICA, dataInicial, dataFinal,predioA,organizador);
		Palestra palestra = new Palestra(Long.valueOf(2), "algoritmos", evento2,  espacoFisico, dataInicial, dataFinal, 50.00);

		inscricao.adicionarAtividade(palestra);
	}
	
	@Test(expected = Exception.class)
	public void incricao_paga_nao_deve_aceitar_novos_itens() throws Exception {
		pagamento = new Pagamento(inscricao, 10.0);
		inscricao.pagarInscricao(pagamento);
		inscricao.adicionarAtividade(evento.getAtividades().get(0));
	}
}
