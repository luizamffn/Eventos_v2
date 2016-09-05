package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.cupom.Cupom;
import br.edu.ifpi.evento.enums.StatusEvento;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EventoSateliteException;
import br.edu.ifpi.evento.exceptions.InstituicaoException;
import br.edu.ifpi.evento.util.Converter;
import br.edu.ifpi.evento.util.Validacoes;

public class Evento {
	private Long id;

	private String nome;
	private List<Atividade> atividades = new ArrayList<Atividade>();
	private TipoEvento tipoEvento;
	private List<Inscricao> inscricoes = new ArrayList<Inscricao>();
	private List<Cupom> Cupons = new ArrayList<Cupom>();
	private List<Instituicao> instituicoes = new ArrayList<>();
	private List<Evento> eventosSatelites = new ArrayList<>();
	private Calendar dataInicio;
	private Calendar dataFim;
	private StatusEvento status;
	private EspacoFisico espacoFisico;

	public Evento(Long id,String nome, TipoEvento tipoEvento, Calendar dataInicio, Calendar dataFim)
			throws DataMenorQueAtualException, DataFimMenorQueDataInicioException {
		verificarDataInicio(dataInicio);
		Validacoes.verificarDataFim(dataInicio, dataFim);
		this.id = id;
		this.nome = nome;
		this.status = StatusEvento.CADASTRADO;
		this.tipoEvento = tipoEvento;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public void verificarDataInicio(Calendar dataInicio) throws DataMenorQueAtualException {
		Calendar now = new GregorianCalendar();

		if (dataInicio.getTimeInMillis()- now.getTimeInMillis() <0 ) {
			throw new DataMenorQueAtualException();
		}
	}

	public void adicionarAtividade(Atividade atividade) throws AtividadeException {
		if (atividades.contains(atividade)) {
			throw new AtividadeException();
		}
		atividades.add(atividade);
	}
	
	public void adicionarInstituicao(Instituicao instituicao) throws InstituicaoException{
		if (instituicoes.contains(instituicao)) {
			throw new InstituicaoException();
		}
		
		instituicoes.add(instituicao);
	}

	public void adicionarIncricao(Inscricao inscricao) {
		inscricoes.add(inscricao);
	}
	
	public void adicionarEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
		espacoFisico.adicionarEvento(this);
	}
	
	public void adicionarEventoSatelite(Evento eventoSatelite) throws EventoSateliteException{
		if (eventosSatelites.contains(eventoSatelite)) {
			throw new EventoSateliteException();
		}
		eventosSatelites.add(eventoSatelite);
	}

	public void gerarAgenda() {
		System.out.println("Agenda de atividade por Evento");
		System.out.println("Espaco Fisico Pai: " + this.espacoFisico.getDescricao());
		for (EspacoFisico espacoFisico : espacoFisico.getEspacoFisicos()) {
			espacoFisico.gerarAgenda();
		}
	}
	
	public boolean isAtivo() {
		Calendar now = new GregorianCalendar();
		return dataFim.getTimeInMillis() < now.getTimeInMillis();
	}
	
	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}

	public List<Cupom> getCupons() {
		return Collections.unmodifiableList(Cupons);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

}
