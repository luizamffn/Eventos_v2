package br.edu.ifpi.evento.modelo.EspacoFisico;

import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.exceptions.EnderecoEspacoFisicoException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoPaiException;
import br.edu.ifpi.evento.modelo.Endereco;

public class EspacoFisicoBuilder {
	private EspacoFisico espacoFisico;
	
	public EspacoFisicoBuilder() {
		this.espacoFisico = new EspacoFisico();
	}
	
	 public static EspacoFisicoBuilder builder() {
	        return new EspacoFisicoBuilder();
	    }

	public EspacoFisicoBuilder id(Long id) {
		this.espacoFisico.setId(id);
		return this;
	}

	public EspacoFisicoBuilder descricao(String descricao) {
		this.espacoFisico.setDescricao(descricao);
		return this;
	}

	public EspacoFisicoBuilder capacidade(int capacidade) {
		this.espacoFisico.setCapacidade(capacidade);
		return this;
	}

	public EspacoFisicoBuilder tipoEspacoFisico(TipoEspacoFisico tipoEspacoFisico) {
		this.espacoFisico.setTipoEspacoFisico(tipoEspacoFisico);
		return this;
	}

	public EspacoFisicoBuilder endereco(Endereco endereco) throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		this.espacoFisico.setEndereco(endereco);
		return this;
	}

	public EspacoFisicoBuilder espacoPai(EspacoFisico espacoPai) {
		this.espacoFisico.setEspacoPai(espacoPai);
		return this;
	}

	public EspacoFisico getEspacoFisico() {
		return this.espacoFisico;
	}
}
