package br.edu.ifpi.evento.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.Atividade.ItemSimples;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;

public class EventosOrganizadoEInscrito {
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException,
			AtividadeException, InscricaoPagaException, AtividadeNaoEstaNoEventoException {
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00, 00);
		EspacoFisico predioA = new EspacoFisico("Predio A", 1000, TipoEspacoFisico.PREDIO);

		Pessoa p = new Pessoa("Josefa", 4454, Sexo.F);
		Usuario organizador = new Usuario("Jose123", "8766Y", p, TipoUsuario.ORGANIZADOR);
		Usuario organizador2 = new Usuario("Jose123ee", "8766Y", p, TipoUsuario.ORGANIZADOR);

		Evento evento = new Evento(Long.valueOf(1), "evento1", TipoEvento.SIMPOSIO, dataInicial, dataFinal, predioA,
				organizador, false);

		Evento evento2 = new Evento(Long.valueOf(1), "Aulao", TipoEvento.SIMPOSIO, dataInicial, dataFinal, predioA,
				organizador, false);

		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 8, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 8, 20, 16, 00, 00);

		EspacoFisico b3_sala4 = new EspacoFisico("B3-04", 40, TipoEspacoFisico.SALA);
		AtividadeCompravel palestra = new AtividadeCompravel(Long.valueOf(1), "python", evento2, b3_sala4, dataInicial2,
				dataFinal2, 30.00, TipoAtividadeCompravel.PALESTRA);
		
		ItemSimples itemSimples = new ItemSimples((long) 1, "palestra", palestra);

		Inscricao inscricao = new Inscricao(evento2, organizador);
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
