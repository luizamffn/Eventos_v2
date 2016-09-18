package br.edu.ifpi.evento.exceptions;

public class EspacoFisicoComAtividadesConflitantes extends Exception {
	public EspacoFisicoComAtividadesConflitantes() {
		super("Está atividade esta chocando horário com outra ja adicionada neste espaco!");
	}
}
