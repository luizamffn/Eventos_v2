package br.edu.ifpi.evento.exceptions;

public class EnderecoEspacoFisicoException extends Exception {
	public EnderecoEspacoFisicoException() {
		super("Este endereço ja possui EspacoFisico!");
	}
}
