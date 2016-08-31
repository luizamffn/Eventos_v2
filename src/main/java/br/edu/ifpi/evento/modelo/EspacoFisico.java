package br.edu.ifpi.evento.modelo;

import java.util.List;

import br.edu.ifpi.evento.enums.TipoEspacoFisico;

public class EspacoFisico {
	private Long id;
	private String descricao;
	private int capacidade;
	private TipoEspacoFisico tipoEspacoFisico;
	private Endereco endereco;
	
	private List<EspacoFisico> espacoFisicos;
	
}
