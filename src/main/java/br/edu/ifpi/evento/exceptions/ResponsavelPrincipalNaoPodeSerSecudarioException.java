package br.edu.ifpi.evento.exceptions;

public class ResponsavelPrincipalNaoPodeSerSecudarioException extends Exception {
	public ResponsavelPrincipalNaoPodeSerSecudarioException() {
		super("Responsavel principal n�o pode ser secundario!");
	}
}
