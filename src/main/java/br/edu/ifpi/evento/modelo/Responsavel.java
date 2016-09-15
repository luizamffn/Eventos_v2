package br.edu.ifpi.evento.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Responsavel{
	
	@Id
	private Long id;
	
	@OneToOne
	private Pessoa pessoa;
	
	@OneToOne
	private Curriculo curriculo;
		
	public void addCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
}
