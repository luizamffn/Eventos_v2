package br.edu.ifpi.evento.exceptions;

public class AtividadeNaoEstaNoEventoException extends Exception {
	public AtividadeNaoEstaNoEventoException() {
		super("Atividade nao esta no evento desta inscricão!");
	}
}
