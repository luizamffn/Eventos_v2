package br.edu.ifpi.evento.Responsavel;

import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.modelo.Curriculo;

public class Responsavel extends Pessoa{
	private Curriculo curriculo;
		
	public Responsavel(String nome, int cpf, Sexo sexo) {
		super(nome, cpf, sexo);
	}

	public void addCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
}
