package br.edu.ifpi.evento.exceptions;

public class UsuarioRepetidoException extends Exception {
	public UsuarioRepetidoException() {
		super("Usuario j� foi adicionado!");
	}
}
