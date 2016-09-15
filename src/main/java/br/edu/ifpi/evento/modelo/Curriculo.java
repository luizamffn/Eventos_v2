package br.edu.ifpi.evento.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Curriculo {
	@Id
	@GeneratedValue
	private Long id;
	private String objetivo;
	private String estadoCivil;
	private Calendar dataNascimento;
	private String nacionalidade;
	private String escolaridade;
}
