package br.edu.ifpi.evento.exceptions;

public class EventoNaoEstaRecebendoInscricaoException extends Exception {
	public EventoNaoEstaRecebendoInscricaoException() {
		super("Este evento n�o esta recebendo inscricao!");
	}
}
