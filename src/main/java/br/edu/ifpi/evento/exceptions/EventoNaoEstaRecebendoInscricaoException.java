package br.edu.ifpi.evento.exceptions;

public class EventoNaoEstaRecebendoInscricaoException extends Exception {
	public EventoNaoEstaRecebendoInscricaoException() {
		super("Este evento não esta recebendo inscricao!");
	}
}
