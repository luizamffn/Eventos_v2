package br.edu.ifpi.evento.modelo.inscricao;

import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.EventoNaoEstaRecebendoInscricaoException;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.evento.Evento;

public class InscricaoBuilder {
	
	private Inscricao inscricao;

	public InscricaoBuilder() {
		inscricao = new Inscricao();
	}
	
	public static InscricaoBuilder builder() {
		return new InscricaoBuilder();
	}

	public InscricaoBuilder Id(Long id) {
		this.inscricao.setId(id);
		return this;
	}
	
	public InscricaoBuilder evento(Evento evento)
 throws InscricaoPagaException, AtividadeNaoEstaNoEventoException,
			AtividadeException, EventoNaoEstaRecebendoInscricaoException {
		this.inscricao.setEvento(evento);
		return this;
	}
	
	public InscricaoBuilder usuario(Usuario usuario) {
		this.inscricao.setUsuario(usuario);
		return this;
	}
	
	public Inscricao getInscricao() {
		return this.inscricao;
	}

}