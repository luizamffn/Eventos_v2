package br.edu.ifpi.evento.exceptions;

public class AtividadeComHorarioForaDoPeriodoDoEvento extends Exception {
	public AtividadeComHorarioForaDoPeriodoDoEvento() {
		super("Atividade com horario fora do periodo do evento!");
	}
}
