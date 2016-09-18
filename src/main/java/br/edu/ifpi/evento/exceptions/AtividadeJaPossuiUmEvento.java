package br.edu.ifpi.evento.exceptions;

public class AtividadeJaPossuiUmEvento extends Exception {
	public AtividadeJaPossuiUmEvento() {
		super("Atividade ja possui um evento!");
	}
}
