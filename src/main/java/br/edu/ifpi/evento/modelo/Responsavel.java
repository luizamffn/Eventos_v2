package br.edu.ifpi.evento.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.Atividade.Atividade;

@Entity
public class Responsavel{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Pessoa pessoa;
	
	@OneToOne
	private Curriculo curriculo;
	
	@ManyToMany(mappedBy="responsaveisSecudarios")
	private List<Atividade> atividades;
	
	public Responsavel() {
	}
	
	public static class ResponsavelBuilder {
		private final Long id;
		private Pessoa pessoa;
		private Curriculo curriculo;
		
		public ResponsavelBuilder(Long id) {
			this.id = id;
		}
		
		public ResponsavelBuilder pessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
			return this;
		}
		
		public ResponsavelBuilder curriculo(Curriculo curriculo) {
			this.curriculo = curriculo;
			return this;
		}
		
		public Responsavel build() {
			return new Responsavel(this);
		}
		
	}
	
	public Responsavel(ResponsavelBuilder builder) {
		id = builder.id;
		pessoa = builder.pessoa;
		curriculo = builder.curriculo;
	}
		
	public void addCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	
	public void addId(Long id) {
		this.id = id;
	}
}
