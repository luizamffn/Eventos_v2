package br.edu.ifpi.evento.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable{
	private List<Observer> observers = new ArrayList<Observer>();
	protected String mensagem;

	public void addObserver(Observer obs) {
		this.observers.add(obs);
	}

	public void delObserver(Observer obs) {
		this.observers.remove(obs);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(mensagem);
		}
	}

	public void notifyUmObserver(Observer obs) {
		obs.update(mensagem);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
