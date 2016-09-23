package br.edu.ifpi.evento.teste.geracao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.StatusEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.EventoNaoEstaRecebendoInscricaoException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.Item.ItemSimples;
import br.edu.ifpi.evento.modelo.Item.ItemSimplesBuilder;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;
import br.edu.ifpi.evento.modelo.inscricao.InscricaoBuilder;

public class ListagemDeInscritos {
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException,
			InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException,
			EspacoFisicoComAtividadesConflitantes, AtividadeHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento,
			EventoNaoEstaRecebendoInscricaoException, EventoSateliteHorarioForaDoPeriodoDoEvento {
		Pessoa pessoa = new Pessoa("Maria", 176, Sexo.F);
		Usuario usuario = new Usuario("Maria_F", "122345", pessoa, TipoUsuario.PARTICIPANTE);

		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = EspacoFisicoBuilder.builder()
				.id((long)1)
				.descricao("PredioA")
				.getEspacoFisico(); 
		
		Pessoa p = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", p, TipoUsuario.ORGANIZADOR);

		Evento evento = EventoBuilder.builder()
				.id((long) 1)
				.dataInicio(dataInicial)
				.dataFim(dataFinal)
				.organizador(organizador)
				.nome("Evento1")
				.status(StatusEvento.RECEBENDO_INSCRICAO)
				.espacoFisico(predioA)
				.getEvento();

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 10, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 10, 20, 16, 00, 00);

		EspacoFisico b3_sala4 = EspacoFisicoBuilder.builder()
				.id((long)1)
				.descricao("B3-04")
				.getEspacoFisico(); 
		
		AtividadeCompravel palestra = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(1))
				.nome("python")
				.horarioInicio(dataInicial2)
				.horarioFim(dataFinal2)
				.evento(evento)
				.espacoFisico(b3_sala4)
				.getAtidadeCompravel(); 

		ItemSimples itemSimples = ItemSimplesBuilder.builder()
				.id((long) 1)
				.atividadeCompravel(palestra)
				.getItemSimples();

		Inscricao inscricao = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(usuario)
				.getInscricao();
		
		inscricao.adicionarItem(itemSimples);

		Pessoa pessoa2 = new Pessoa("Joao", 9888, Sexo.M);
		Usuario usuario2 = new Usuario("João1", "12#1", pessoa2, TipoUsuario.PARTICIPANTE);
		Inscricao inscricao2 = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(usuario2)
				.getInscricao();
		inscricao2.adicionarItem(itemSimples);

//		itemSimples.listaInscritos();

		b3_sala4.listaInscritos();
	}

}

