package br.edu.ifpi.evento.exceptions;

public class ResponsavelPrincipalNaoPodeSerSecudarioException extends Exception {
	public ResponsavelPrincipalNaoPodeSerSecudarioException() {
		super("Responsavel principal não pode ser secundario!");
	}
}
