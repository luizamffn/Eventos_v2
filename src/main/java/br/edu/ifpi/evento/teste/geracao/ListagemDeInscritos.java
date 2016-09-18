package br.edu.ifpi.evento.teste.geracao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.Atividade.Item;
import br.edu.ifpi.evento.Atividade.ItemSimples;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;

public class ListagemDeInscritos {
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException,
			InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException,
			EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
		Pessoa pessoa = new Pessoa("Maria", 176, Sexo.F);
		Usuario usuario = new Usuario("Maria_F", "122345", pessoa, TipoUsuario.PARTICIPANTE);

		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = new EspacoFisico.EspacoFisicoBuilder((long) 1).descricao("Predio A").build();

		Pessoa p = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", p, TipoUsuario.ORGANIZADOR);

		Evento evento = new Evento.EventoBuilder((long) 1, dataInicial, dataFinal, organizador)
				.nome("Evento1")
				.espacoFisico(predioA)
				.build();

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 10, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 10, 20, 16, 00, 00);

		EspacoFisico b3_sala4 = new EspacoFisico.EspacoFisicoBuilder((long) 1).descricao("B3-04").build();
		AtividadeCompravel palestra = new AtividadeCompravel(Long.valueOf(1), "python", evento, b3_sala4, dataInicial2, dataFinal2, 30.00,TipoAtividadeCompravel.PALESTRA);

		ItemSimples itemSimples = new ItemSimples.ItemSimplesBuilder((long) 1)
				.atividadeCompravel(palestra)
				.build();

		Inscricao inscricao = new Inscricao.InscricaoBuilder(evento, usuario).build();
		inscricao.adicionarItem(itemSimples);

		Pessoa pessoa2 = new Pessoa("Joao", 9888, Sexo.M);
		Usuario usuario2 = new Usuario("João1", "12#1", pessoa2, TipoUsuario.PARTICIPANTE);
		Inscricao inscricao2 = new Inscricao.InscricaoBuilder(evento, usuario2).build();
		inscricao2.adicionarItem(itemSimples);

		// palestra.listaInscritos();

		b3_sala4.listaInscritos();
	}

}

