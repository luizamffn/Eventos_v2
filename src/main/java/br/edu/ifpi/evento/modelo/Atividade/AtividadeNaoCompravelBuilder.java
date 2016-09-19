package br.edu.ifpi.evento.modelo.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.Responsavel.Responsavel;
import br.edu.ifpi.evento.modelo.evento.Evento;

public class AtividadeNaoCompravelBuilder {	
	private AtividadeNaoCompravel atividadeNaoCompravel;
	
	public AtividadeNaoCompravelBuilder() {
		atividadeNaoCompravel = new AtividadeNaoCompravel();
	}
	
	public static AtividadeNaoCompravelBuilder builder() {
		return new AtividadeNaoCompravelBuilder();
	}
	
	public AtividadeNaoCompravelBuilder id(Long id){
		this.atividadeNaoCompravel.setId(id);
		return this;
	}
	
	public AtividadeNaoCompravelBuilder horarioInicio(Calendar horarioInicio){
		this.atividadeNaoCompravel.setHorarioInicio(horarioInicio);
		return this;
	}
	
	public AtividadeNaoCompravelBuilder horarioFim(Calendar horarioFim) throws DataFimMenorQueDataInicioException{
		this.atividadeNaoCompravel.setHorarioTermino(horarioFim);
		return this;
	}
	
	public AtividadeNaoCompravelBuilder nome(String nome) {
		this.atividadeNaoCompravel.setNome(nome);
		return this;
	}
	
	public AtividadeNaoCompravelBuilder evento(Evento evento) throws AtividadeJaPossuiUmEvento, AtividadeException, EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento {
		this.atividadeNaoCompravel.setEvento(evento);
		return this;
	}

	public AtividadeNaoCompravelBuilder espacoFisico(EspacoFisico espacoFisico) throws EspacoFisicoComAtividadesConflitantes {
		this.atividadeNaoCompravel.setEspacoFisico(espacoFisico);
		return this;
	}
	
	public AtividadeNaoCompravelBuilder responsavelPrincipal(Responsavel responsavelPrincipal) {
		this.atividadeNaoCompravel.setResponsavelPrincipal(responsavelPrincipal);
		return this;
	}
	
	public AtividadeNaoCompravel getAtividadeNaoCompravel() {
		return this.atividadeNaoCompravel;
	}
}
