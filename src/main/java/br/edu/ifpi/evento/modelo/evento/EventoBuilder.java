package br.edu.ifpi.evento.modelo.evento;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.StatusEvento;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;

public class EventoBuilder {	
	private Evento evento;
	
	public EventoBuilder() {
		evento = new Evento();
		evento.setStatus(StatusEvento.CADASTRADO);
	}
	
	public static EventoBuilder builder() {
        return new EventoBuilder();
    }
	
	public EventoBuilder nome(String nome) {
		this.evento.setNome(nome);
		return this;
	}
	public EventoBuilder id(Long id) {
		this.evento.setId(id);
		return this;
	}
	
	public EventoBuilder dataInicio(Calendar dataInicio) throws DataMenorQueAtualException {
		this.evento.setDataInicio(dataInicio);
		return this;
	}
	
	public EventoBuilder dataFim(Calendar dataFim) throws DataFimMenorQueDataInicioException {
		this.evento.setDataFim(dataFim);
		return this;
	}
	
	public EventoBuilder organizador(Usuario organizador) {
		this.evento.setOrganizador(organizador);
		return this;
	}
	public EventoBuilder tipoEvento(TipoEvento tipoEvento) {
		this.evento.setTipoEvento(tipoEvento);
		return this;
	}
	
	public EventoBuilder espacoFisico(EspacoFisico espacoFisico) {
		this.evento.setEspacoFisico(espacoFisico);
		return this;
	}
	
	public EventoBuilder eventoUnico(boolean eventoUnico) {
		this.evento.setEventoUnico(eventoUnico);
		return this;
	}
	
	public EventoBuilder eventoPai(Evento eventoPai) {
		this.evento.setEventoPai(eventoPai);
		return this;
	}
	
	public Evento getEvento(){
		return this.evento;
	}
}
