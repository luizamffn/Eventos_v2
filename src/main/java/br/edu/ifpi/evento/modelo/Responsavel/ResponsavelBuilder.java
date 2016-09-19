package br.edu.ifpi.evento.modelo.Responsavel;

import br.edu.ifpi.evento.modelo.Curriculo;
import br.edu.ifpi.evento.modelo.Pessoa;

public class ResponsavelBuilder {
	private Responsavel responsavel;
	
	public ResponsavelBuilder() {
		responsavel = new Responsavel();
	}
	
	public static ResponsavelBuilder builder(){
		return new ResponsavelBuilder();
	}
	
	public ResponsavelBuilder id(Long id) {
		this.responsavel.setId(id);
		return this;
	}
	
	public ResponsavelBuilder pessoa(Pessoa pessoa) {
		this.responsavel.setPessoa(pessoa);
		return this;
	}
	
	public ResponsavelBuilder curriculo(Curriculo curriculo) {
		this.responsavel.setCurriculo(curriculo);
		return this;
	}
	
	public Responsavel getResponsavel() {
		return this.responsavel;
	}
	
}
