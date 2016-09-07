package br.edu.ifpi.evento.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.edu.ifpi.evento.Atividade.Palestra;
import br.edu.ifpi.evento.enums.Sexo;
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

public class ListagemDeInscritos {
	public static void main(String[] args) throws DataMenorQueAtualException, DataFimMenorQueDataInicioException, InscricaoPagaException, AtividadeNaoEstaNoEventoException, AtividadeException {
		Pessoa pessoa = new Pessoa("Maria", 176, Sexo.F);
		Usuario usuario = new Usuario("Maria_F", "122345", pessoa, TipoUsuario.PARTICIPANTE);
		
		GregorianCalendar dataInicial = new GregorianCalendar();
		dataInicial.set(2016, 10, 12, 12, 00, 00);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2016, 10, 20, 20, 00,00);
		Evento evento = new Evento(Long.valueOf(1), "evento1", TipoEvento.SIMPOSIO, dataInicial, dataFinal);
		
		EspacoFisico predioA = new EspacoFisico("Predio A", 1000,TipoEspacoFisico.PREDIO);
		evento.adicionarEspacoFisico(predioA);
		
		GregorianCalendar dataInicial2 = new GregorianCalendar();
		dataInicial2.set(2016, 8, 12, 14, 00, 00);
		Calendar dataFinal2 = Calendar.getInstance();
		dataFinal2.set(2016, 8, 20, 16, 00,00);

		EspacoFisico b3_sala4 = new EspacoFisico("B3-04", 40,TipoEspacoFisico.SALA);
		Palestra palestra = new Palestra(Long.valueOf(1), "python", evento, b3_sala4,dataInicial2,dataFinal2, 30.00);
		
		Inscricao inscricao = new Inscricao(evento, usuario);
		inscricao.adicionarAtividade(palestra);
		
		Pessoa pessoa2 = new Pessoa("Joao", 9888, Sexo.M);
		Usuario usuario2 = new Usuario("João1", "12#1", pessoa2, TipoUsuario.PARTICIPANTE);
		Inscricao inscricao2 = new Inscricao(evento, usuario2);
		inscricao2.adicionarAtividade(palestra);
		
//		palestra.listaInscritos();
		
		b3_sala4.listaInscritos();
	}
	
}
