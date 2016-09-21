package br.edu.ifpi.evento.teste.modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.CupomException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.Pagamento;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.Atividade.Item;
import br.edu.ifpi.evento.modelo.Atividade.ItemComposto;
import br.edu.ifpi.evento.modelo.Atividade.ItemSimples;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.cupom.Palestras_50;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;
import br.edu.ifpi.evento.modelo.inscricao.InscricaoBuilder;

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
		validadePalestra.set(2016, 12, 12, 20, 00);

		Pessoa pessoa = new Pessoa("Josefa", 4454, Sexo.F);
		organizador = new Usuario("Jose123", "8766Y", pessoa, TipoUsuario.ORGANIZADOR);

		espacoFisico = EspacoFisicoBuilder.builder()
				.id((long)1)
				.descricao("Instituto Federal")
				.getEspacoFisico(); 
		
		evento = EventoBuilder.builder()
				.id((long) 1)
				.dataInicio(dataInicial)
				.dataFim(dataFinal)
				.organizador(organizador)
				.espacoFisico(espacoFisico)
				.getEvento();

		Calendar dataInicialAt = new GregorianCalendar();
		dataInicialAt.set(2016, 12, 11, 20, 44);
		Calendar dataFinalAt = Calendar.getInstance();
		dataFinalAt.set(2016, 12, 11, 22, 00);
		
		espacoFisico = EspacoFisicoBuilder.builder()
				.id((long)1)
				.descricao("PredioA")
				.getEspacoFisico(); 
		
		AtividadeCompravel atividade = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(1))
				.horarioInicio(dataInicialAt)
				.horarioFim(dataFinalAt)
				.evento(evento)
				.espacoFisico(espacoFisico)
				.valor(20.00)
				.tipo(TipoAtividadeCompravel.PALESTRA)
				.getAtidadeCompravel();

		inscricao = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(organizador)
				.getInscricao();
		
		itemSimples = new ItemSimples.ItemSimplesBuilder((long) 1)
				.atividadeCompravel(atividade)
				.build();
		inscricao.adicionarItem(itemSimples);

		Palestras_50 palestras_50 = new Palestras_50("p50", validadePalestra);
		evento.adicionarCupons(palestras_50);

	}

	@Test
	public void nao_deve_aplicar_descontos_de_cupons_nao_ativos() throws Exception {
		Calendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 12, 13, 8, 00);
		Calendar dataFinal= Calendar.getInstance();
		dataFinal.set(2016, 12, 13, 9, 00);
		
		AtividadeCompravel atividade = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(2))
				.horarioInicio(dataInicial)
				.horarioFim(dataFinal)
				.evento(evento)
				.espacoFisico(espacoFisico)
				.valor(40.00)
				.getAtidadeCompravel();
				
		ItemSimples itemSimples = new ItemSimples.ItemSimplesBuilder((long) 2)
				.atividadeCompravel(atividade)
				.build();

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
	public void inscricao_recem_criada_deve_ter_zero_atividades() throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		Inscricao inscricao2 = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(new Usuario())
				.getInscricao();
		
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
		
		AtividadeCompravel palestra = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(2))
				.horarioInicio(dataInicial)
				.horarioFim(dataFinal)
				.evento(evento)
				.espacoFisico(espacoFisico)
				.getAtidadeCompravel();
				
		ItemSimples itemSimples = new ItemSimples.ItemSimplesBuilder((long) 2)
				.atividadeCompravel(palestra)
				.build();
		inscricao.adicionarItem(itemSimples);
	}

	@Test
	public void inscricao_sem_itens_deve_ter_valor_zero() throws InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		Inscricao inscricao3 = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(new Usuario())
				.getInscricao();
		
		assertEquals(0.0, inscricao3.getValorTotal(), 0.0);
	}

	@Test
	public void inscricao_deve_aplicar_descontos_ativos_no_evento() throws CupomException {
		inscricao.getValorTotal();
	}

	@Test(expected = Exception.class)
	public void nao_deve_aceitar_incluir_atividades_de_outros_eventos() throws Exception {
		EspacoFisico predioA = EspacoFisicoBuilder.builder()
				.id((long)1)
				.descricao("PredioA")
				.getEspacoFisico(); 
		
		Evento evento2 = EventoBuilder.builder()
				.id((long) 2)
				.dataInicio(dataInicial)
				.dataFim(dataFinal)
				.organizador(organizador)
				.espacoFisico(predioA)
				.getEvento();
		
		AtividadeCompravel palestra = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(2))
				.horarioInicio(dataInicial)
				.horarioFim(dataFinal)
				.evento(evento2)
				.espacoFisico(espacoFisico)
				.getAtidadeCompravel();
				
		ItemSimples itemSimples = new ItemSimples.ItemSimplesBuilder((long) 2)
				.atividadeCompravel(palestra)
				.build();;
		inscricao.adicionarItem(itemSimples);
	}

	@Test(expected = Exception.class)
	public void incricao_paga_nao_deve_aceitar_novos_itens() throws Exception {
		pagamento = new Pagamento(inscricao, 10.0);
		inscricao.pagarInscricao(pagamento);

		inscricao.adicionarItem(itemSimples);
	}

	public void adcionar_todas_as_atividades_em_incricao_se_o_evento_for_unico() throws Exception {
		Evento evento = EventoBuilder.builder()
				.id((long) 2)
				.dataInicio(dataInicial)
				.dataFim(dataFinal)
				.organizador(organizador)
				.espacoFisico(espacoFisico)
				.getEvento();
		
		AtividadeCompravel palestra = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(1))
				.horarioInicio(dataInicial)
				.horarioFim(dataFinal)
				.evento(evento)
				.espacoFisico(espacoFisico)
				.getAtidadeCompravel();
				
		AtividadeCompravel minicurso = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(2))
				.horarioInicio(dataInicial)
				.horarioFim(dataFinal)
				.evento(evento)
				.espacoFisico(espacoFisico)
				.getAtidadeCompravel();
				
		List<AtividadeCompravel> compravel = new ArrayList<AtividadeCompravel>();
		Inscricao inscricao = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(new Usuario())
				.getInscricao();
		
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