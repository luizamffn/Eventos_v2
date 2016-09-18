package br.edu.ifpi.evento.teste.modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.Atividade.Item;
import br.edu.ifpi.evento.Atividade.ItemComposto;
import br.edu.ifpi.evento.Atividade.ItemSimples;
import br.edu.ifpi.evento.cupom.Palestras_50;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.CupomException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Pagamento;
import br.edu.ifpi.evento.modelo.Pessoa;
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
	ItemSimples itemSimples;

	@Before
	public void init() throws Exception {
		dataInicial = new GregorianCalendar();
		dataFinal = Calendar.getInstance();
		dataInicial.set(2016, 12, 10, 20, 44);
		dataFinal.set(2016, 12, 20, 22, 00);
		validadePalestra.set(2016, 9, 12, 20, 44);

		Pessoa pessoa = new Pessoa("Josefa", 4454, Sexo.F);
		organizador = new Usuario("Jose123", "8766Y", pessoa, TipoUsuario.ORGANIZADOR);

		espacoFisico = new EspacoFisico.EspacoFisicoBuilder((long) 1).descricao("Instituto Federal").build();
		evento = new Evento((long) 1, "teste1", TipoEvento.CONGRESSO, dataInicial, dataFinal, espacoFisico, organizador,
				false);

		Calendar dataInicialAt = new GregorianCalendar();
		dataInicialAt.set(2016, 12, 11, 20, 44);
		Calendar dataFinalAt = Calendar.getInstance();
		dataFinalAt.set(2016, 12, 11, 22, 00);
		
		espacoFisico = new EspacoFisico.EspacoFisicoBuilder((long) 2).descricao("PredioA").build();
		AtividadeCompravel atividade = new AtividadeCompravel(Long.valueOf(1), "java pra web", evento, espacoFisico,
				dataInicialAt, dataFinalAt, 20.00, TipoAtividadeCompravel.PALESTRA);

		inscricao = new Inscricao(evento, organizador);

		itemSimples = new ItemSimples((long) 1, "palestra", atividade);
		inscricao.adicionarItem(itemSimples);

		Palestras_50 palestras_50 = new Palestras_50("p50", validadePalestra);
		evento.adicionarCupons(palestras_50);

		// evento.gerarAgenda();
	}

	@Test
	public void nao_deve_aplicar_descontos_de_cupons_nao_ativos() throws Exception {
		Calendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 12, 13, 8, 00);
		Calendar dataFinal= Calendar.getInstance();
		dataFinal.set(2016, 12, 13, 9, 00);
		
		AtividadeCompravel atividade = new AtividadeCompravel(Long.valueOf(2), "html", evento, espacoFisico,
				dataInicial, dataFinal, 40.00, TipoAtividadeCompravel.PALESTRA);

		ItemSimples itemSimples = new ItemSimples((long) 2, "palestra", atividade);

		inscricao.adicionarItem(itemSimples);
		System.out.println("valor" + inscricao.getValorTotal());
	}

	@Test
	public void valor_da_inscricao_eh_o_total_dos_seus_itens() {
		System.out.println(inscricao.getValorTotal());
		assertEquals(10.0, inscricao.getValorTotal(), 0.0);
	}

	@Test
	public void deve_marcar_inscricao_como_paga_aoo_receber_pagamento() throws Exception {
		pagamento = new Pagamento(inscricao, 20.0);
		inscricao.pagarInscricao(pagamento);
		assertEquals(true, inscricao.isPaga());
	}

	@Test(expected = Exception.class)
	public void nao_deve_icluir_atividades_repetidas() throws Exception {
		inscricao.adicionarItem(itemSimples);
	}

	@Test
	public void inscricao_recem_criada_deve_ter_zero_atividades() {
		Inscricao inscricao2 = new Inscricao(evento);
		int tamanho = inscricao2.getItens().size();
		assertEquals(0, tamanho);

	}

	@Test(expected = Exception.class)
	public void inscricoes_com_pagamentos_inferiores_ao_valor_a_pagar_devem_ser_invalidos() throws Exception {
		pagamento = new Pagamento(inscricao, 9.0);
		inscricao.pagarInscricao(pagamento);
	}

	@Test
	public void deve_aceitar_incluir_atividades_que_estejam_no_seu_evento() throws Exception {
		Calendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 12, 13, 8, 00);
		Calendar dataFinal= Calendar.getInstance();
		dataFinal.set(2016, 12, 13, 10, 00);
		
		AtividadeCompravel palestra = new AtividadeCompravel(Long.valueOf(2), "algoritmos", evento, espacoFisico,
				dataInicial, dataFinal, 50.00, TipoAtividadeCompravel.PALESTRA);

		ItemSimples itemSimples = new ItemSimples((long) 2, "palestra", palestra);
		inscricao.adicionarItem(itemSimples);
	}

	@Test
	public void inscricao_sem_itens_deve_ter_valor_zero() {
		Inscricao inscricao3 = new Inscricao(evento);
		assertEquals(0.0, inscricao3.getValorTotal(), 0.0);
	}

	@Test
	public void inscricao_deve_aplicar_descontos_ativos_no_evento() throws CupomException {
		inscricao.getValorTotal();
	}

	@Test(expected = Exception.class)
	public void nao_deve_aceitar_incluir_atividades_de_outros_eventos() throws Exception {
		EspacoFisico predioA = new EspacoFisico.EspacoFisicoBuilder((long) 3).descricao("PredioA").build();;

		Evento evento2 = new Evento(Long.valueOf(2), "evento2", TipoEvento.SEMANA_CIENTIFICA, dataInicial, dataFinal,
				predioA, organizador, false);
		AtividadeCompravel palestra = new AtividadeCompravel(Long.valueOf(2), "algoritmos", evento2, espacoFisico,
				dataInicial, dataFinal, 50.00, TipoAtividadeCompravel.PALESTRA);

		ItemSimples itemSimples = new ItemSimples((long) 2, "palestra", palestra);
		inscricao.adicionarItem(itemSimples);
	}

	@Test(expected = Exception.class)
	public void incricao_paga_nao_deve_aceitar_novos_itens() throws Exception {
		pagamento = new Pagamento(inscricao, 10.0);
		inscricao.pagarInscricao(pagamento);

		inscricao.adicionarItem(itemSimples);
	}

	public void adcionar_todas_as_atividades_em_incricao_se_o_evento_for_unico() throws Exception {
		Evento evento = new Evento((long) 7, "Programacao", TipoEvento.SIMPOSIO, dataInicial, dataFinal, espacoFisico,
				organizador, true);
		AtividadeCompravel palestra = new AtividadeCompravel((long) 1, "palesta", evento, espacoFisico, dataInicial,
				dataFinal, 40.00, TipoAtividadeCompravel.PALESTRA);
		AtividadeCompravel minicurso = new AtividadeCompravel((long) 2, "minicurso", evento, espacoFisico, dataInicial,
				dataFinal, 50.00, TipoAtividadeCompravel.MINICURSO);

		List<AtividadeCompravel> compravel = new ArrayList<AtividadeCompravel>();
		Inscricao inscricao = new Inscricao(evento, organizador);
		for (Atividade atividade : evento.getAtividades()) {
			if (atividade instanceof AtividadeCompravel) {
				compravel.add((AtividadeCompravel) atividade);
			}
		}

		List<AtividadeCompravel> atividadesInscricao = new ArrayList<AtividadeCompravel>();
		for (Item item : inscricao.getItens()) {
			if (item instanceof ItemSimples) {
				atividadesInscricao.add(((ItemSimples) item).getAtividadeCompravel());
			}

			if (item instanceof ItemComposto) {
				for (ItemSimples item2 : ((ItemComposto) item).getItensSimples()) {
					atividadesInscricao.add(item2.getAtividadeCompravel());
				}
			}
		}
		assertEquals(true, compravel.equals(atividadesInscricao));
	}
}