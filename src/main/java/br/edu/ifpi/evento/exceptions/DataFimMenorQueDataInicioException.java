package br.edu.ifpi.evento.exceptions;

public class DataFimMenorQueDataInicioException extends Exception{
	public DataFimMenorQueDataInicioException() {
		super("A data final não pode ser menor que a data inicio!");
	}
}
