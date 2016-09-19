package br.edu.ifpi.evento.modelo.Responsavel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.modelo.Curriculo;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;

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
		
	public void addCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
}
