package br.edu.ifpi.evento.modelo.Atividade;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoNaoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeComHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.evento.Evento;

@Entity
@DiscriminatorValue(value = "At_nao_compravel")
public class AtividadeNaoCompravel extends Atividade {

	@Enumerated(EnumType.STRING)
	private TipoNaoAtividadeCompravel tipoNaoCompravel;

	public AtividadeNaoCompravel() {
	}

	public TipoNaoAtividadeCompravel getTipoNaoCompravel() {
		return tipoNaoCompravel;
	}

}
