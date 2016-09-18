package br.edu.ifpi.evento.exceptions;

public class ItemSimplesJaAdicionadoException extends Exception {
	public ItemSimplesJaAdicionadoException() {
		super("O item ja foi adicionado!");
	}
}
