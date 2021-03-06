package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.edu.ifpi.evento.enums.TipoInstituicao;
import br.edu.ifpi.evento.modelo.evento.Evento;

@Entity
public class Instituicao {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int cnpg;
	
	@Enumerated(EnumType.STRING)
	private TipoInstituicao tipoInstituicao;
	
	@ManyToMany(mappedBy="instituicoes")
	private List<Evento> eventos = new ArrayList<Evento>();
	
	public Instituicao() {
	}
	
	public Instituicao(String nome, int cnpg, TipoInstituicao tipoInstituicao) {
		super();
		this.nome = nome;
		this.cnpg = cnpg;
		this.tipoInstituicao = tipoInstituicao;
	}
	
	public void addEvento(Evento evento) {
		eventos.add(evento);
	}

	public List<Evento> getEventos() {
		return Collections.unmodifiableList(eventos);
	}
}
