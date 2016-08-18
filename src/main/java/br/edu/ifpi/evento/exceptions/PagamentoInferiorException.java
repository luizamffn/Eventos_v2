package br.edu.ifpi.evento.exceptions;

public class PagamentoInferiorException extends Exception{
	public PagamentoInferiorException() {
		super("Valor inferior ao total!");
	}
}
