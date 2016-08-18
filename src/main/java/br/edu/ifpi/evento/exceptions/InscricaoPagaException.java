package br.edu.ifpi.evento.exceptions;

public class InscricaoPagaException extends Exception{
	public InscricaoPagaException(){
		super("Inscricao ja esta paga, nao pode mais adicionar atividades!");
	}
}
