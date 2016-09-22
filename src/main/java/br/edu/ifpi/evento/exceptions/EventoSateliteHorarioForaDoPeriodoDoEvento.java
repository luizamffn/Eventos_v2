package br.edu.ifpi.evento.exceptions;

public class EventoSateliteHorarioForaDoPeriodoDoEvento extends Exception {
	public EventoSateliteHorarioForaDoPeriodoDoEvento() {
		super("Evento satelite com horario fora do periodo do evento!");
	}
}
