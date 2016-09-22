package br.edu.ifpi.evento.exceptions;

public class AtividadeHorarioForaDoPeriodoDoEvento extends Exception {
	public AtividadeHorarioForaDoPeriodoDoEvento() {
		super("Atividade com horario fora do periodo do evento!");
	}
}
