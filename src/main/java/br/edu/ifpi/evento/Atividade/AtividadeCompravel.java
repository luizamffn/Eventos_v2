package br.edu.ifpi.evento.Atividade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;
import br.edu.ifpi.evento.modelo.Inscricao;

public class AtividadeCompravel extends Atividade {
	private Double valor;
	private List<Inscricao> inscricoes = new ArrayList<>();
	private TipoAtividadeCompravel tipo;
	
	public AtividadeCompravel(Long id, String nome, Evento evento, EspacoFisico espacoFisico, Calendar hoharioInicio,
			Calendar hoharioTermino, Double valor, TipoAtividadeCompravel tipo) throws DataFimMenorQueDataInicioException, AtividadeException {
		super(id, nome, evento, espacoFisico, hoharioInicio, hoharioTermino);
		this.valor = valor;
		this.tipo = tipo;
	}

	public void adicionarInscricao(Inscricao inscricao) {
		inscricoes.add(inscricao);
	}
	public Double getValor() {
		return valor;
	}
	
	public void listaInscritos() {
		System.out.println("Nome dos inscritos:");
		for (Inscricao inscricao : inscricoes) {
			System.out.println("\t" + inscricao.getUsuario().getPessoa().getNome());
		}
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public TipoAtividadeCompravel getTipo() {
		return tipo;
	}
}
