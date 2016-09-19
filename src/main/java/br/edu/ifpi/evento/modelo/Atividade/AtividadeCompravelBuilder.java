package br.edu.ifpi.evento.modelo.Atividade;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.Responsavel.Responsavel;
import br.edu.ifpi.evento.modelo.evento.Evento;

public class AtividadeCompravelBuilder {	
	private AtividadeCompravel atividadeCompravel;
	
	public AtividadeCompravelBuilder() {
		atividadeCompravel = new AtividadeCompravel();
	}
	
	public static AtividadeCompravelBuilder builder() {
		return new AtividadeCompravelBuilder();
	}
	
	public AtividadeCompravelBuilder id(Long id){
		this.atividadeCompravel.setId(id);
		return this;
	}
	
	public AtividadeCompravelBuilder valor(double valor){
		this.atividadeCompravel.setValor(valor);
		return this;
	}
	
	public AtividadeCompravelBuilder tipo(TipoAtividadeCompravel tipo){
		this.atividadeCompravel.setTipoCompravel(tipo);
		return this;
	}
	
	public AtividadeCompravelBuilder horarioInicio(Calendar horarioInicio){
		this.atividadeCompravel.setHorarioInicio(horarioInicio);
		return this;
	}
	
	public AtividadeCompravelBuilder horarioFim(Calendar horarioFim) throws DataFimMenorQueDataInicioException{
		this.atividadeCompravel.setHorarioTermino(horarioFim);
		return this;
	}
	
	public AtividadeCompravelBuilder nome(String nome) {
		this.atividadeCompravel.setNome(nome);
		return this;
	}
	
	public AtividadeCompravelBuilder evento(Evento evento) throws AtividadeJaPossuiUmEvento, AtividadeException, EspacoFisicoComAtividadesConflitantes, AtividadeComHorarioForaDoPeriodoDoEvento {
		this.atividadeCompravel.setEvento(evento);
		return this;
	}

	public AtividadeCompravelBuilder espacoFisico(EspacoFisico espacoFisico) throws EspacoFisicoComAtividadesConflitantes {
		this.atividadeCompravel.setEspacoFisico(espacoFisico);
		return this;
	}
	
	public AtividadeCompravelBuilder responsavelPrincipal(Responsavel responsavelPrincipal) {
		this.atividadeCompravel.setResponsavelPrincipal(responsavelPrincipal);
		return this;
	}
	
	public AtividadeCompravel getAtidadeCompravel() {
		return this.atividadeCompravel;
	}
}
