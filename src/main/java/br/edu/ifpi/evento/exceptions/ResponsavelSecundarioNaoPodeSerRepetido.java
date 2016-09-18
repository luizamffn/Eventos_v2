package br.edu.ifpi.evento.exceptions;

public class ResponsavelSecundarioNaoPodeSerRepetido extends Exception {
	public ResponsavelSecundarioNaoPodeSerRepetido() {
		super("Responsavel secundario ja adicionado!");
	}
}
