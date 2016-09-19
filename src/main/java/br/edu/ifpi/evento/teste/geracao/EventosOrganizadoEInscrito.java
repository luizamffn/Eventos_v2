package br.edu.ifpi.evento.teste.geracao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.Atividade.ItemSimples;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;
import br.edu.ifpi.evento.modelo.inscricao.InscricaoBuilder;

public class EventosOrganizadoEInscrito {
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException,
			AtividadeException, InscricaoPagaException, AtividadeNaoEstaNoEventoException,
			EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = EspacoFisicoBuilder.builder()
				.id((long)1)
				.getEspacoFisico(); 

		Pessoa p = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", p, TipoUsuario.ORGANIZADOR);
		Usuario organizador2 = new Usuario("Jose123ee", "8766Y", p, TipoUsuario.ORGANIZADOR);

		Evento evento = EventoBuilder.builder()
				.id((long) 1)
				.dataInicio(dataInicial)
				.dataFim(dataFinal)
				.organizador(organizador)
				.nome("Evento1")
				.espacoFisico(predioA)
				.getEvento();

		Evento evento2 = EventoBuilder.builder()
				.id((long) 2)
				.dataInicio(dataInicial)
				.dataFim(dataFinal)
				.organizador(organizador)
				.nome("Aulão")
				.espacoFisico(predioA)
				.getEvento();

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 10, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 10, 12, 16, 00, 00);

		EspacoFisico b3_sala4 = EspacoFisicoBuilder.builder()
				.id((long)2)
				.descricao("PredioA")
				.getEspacoFisico();
		
		AtividadeCompravel palestra = AtividadeCompravelBuilder.builder()
				.id(Long.valueOf(1))
				.nome("python")
				.horarioInicio(dataInicial2)
				.horarioFim(dataFinal2)
				.evento(evento2)
				.espacoFisico(b3_sala4)
				.getAtidadeCompravel();
		
//				new AtividadeCompravel(Long.valueOf(1), "python", evento2, b3_sala4, dataInicial2,
//				dataFinal2, 30.00, TipoAtividadeCompravel.PALESTRA);

		ItemSimples itemSimples = new ItemSimples.ItemSimplesBuilder((long) 1)
				.atividadeCompravel(palestra)
				.build();

		Inscricao inscricao = InscricaoBuilder.builder()
				.evento(evento2)
				.usuario(organizador)
				.getInscricao();
		
		inscricao.adicionarItem(itemSimples);

		System.out.println("Eventos Organizados: ");
		for (Evento e : organizador.getEventosOrganizados()) {
			System.out.println("\t" + e.getNome());
		}

		System.out.println("Eventos Participado: ");
		for (Inscricao ins : organizador.getInscricoes()) {
			System.out.println("\t" + ins.getEvento().getNome());
		}
	}
}
