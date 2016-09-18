package br.edu.ifpi.evento.teste.geracao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
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

public class EventosOrganizadoEInscrito {
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException,
			AtividadeException, InscricaoPagaException, AtividadeNaoEstaNoEventoException,
			EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento, AtividadeJaPossuiUmEvento {
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = new EspacoFisico.EspacoFisicoBuilder((long) 1).build();

		Pessoa p = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", p, TipoUsuario.ORGANIZADOR);
		Usuario organizador2 = new Usuario("Jose123ee", "8766Y", p, TipoUsuario.ORGANIZADOR);

		Evento evento = new Evento.EventoBuilder((long) 1, dataInicial, dataFinal, organizador)
				.nome("Evento1")
				.espacoFisico(predioA)
				.build();

		Evento evento2 = new Evento.EventoBuilder((long) 2, dataInicial, dataFinal, organizador)
				.nome("Aulão")
				.espacoFisico(predioA)
				.build();

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 10, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 10, 12, 16, 00, 00);

		EspacoFisico b3_sala4 = new EspacoFisico.EspacoFisicoBuilder((long) 2).build();
		AtividadeCompravel palestra = new AtividadeCompravel(Long.valueOf(1), "python", evento2, b3_sala4, dataInicial2,
				dataFinal2, 30.00, TipoAtividadeCompravel.PALESTRA);

		ItemSimples itemSimples = new ItemSimples((long) 1, "palestra", palestra);

		Inscricao inscricao = new Inscricao.InscricaoBuilder(evento2, organizador).build();
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
