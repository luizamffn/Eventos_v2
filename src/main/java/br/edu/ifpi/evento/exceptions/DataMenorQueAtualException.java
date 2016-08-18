package br.edu.ifpi.evento.exceptions;

public class DataMenorQueAtualException extends Exception{
	public DataMenorQueAtualException() {
		super("A data nao pode ser menor que a atual!");
	}
}
