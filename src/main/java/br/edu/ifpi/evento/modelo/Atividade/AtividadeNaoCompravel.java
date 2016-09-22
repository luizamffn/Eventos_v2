package br.edu.ifpi.evento.modelo.Atividade;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.edu.ifpi.evento.enums.TipoNaoAtividadeCompravel;

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
