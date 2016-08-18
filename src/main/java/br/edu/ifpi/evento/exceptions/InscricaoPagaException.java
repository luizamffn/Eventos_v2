package br.edu.ifpi.evento.exceptions;

public class InscricaoPagaException extends Exception{
	public InscricaoPagaException(){
		super("A inscricao ja esta paga!");
	}
}
