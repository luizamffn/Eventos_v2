package br.edu.ifpi.evento.exceptions;

public class EspacoFisicoComAtividadesConflitantes extends Exception {
	public EspacoFisicoComAtividadesConflitantes() {
		super("Est� atividade esta chocando hor�rio com outra ja adicionada neste espaco!");
	}
}
