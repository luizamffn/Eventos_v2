package br.edu.ifpi.evento.exceptions;

public class DataFimMenorQueDataInicioException extends Exception{
	public DataFimMenorQueDataInicioException() {
		super("A data final n�o pode ser menor que a data inicio!");
	}
}
