package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.util.Converter;

public class EspacoFisico {
	private Long id;
	private String descricao;
	private int capacidade;
	private TipoEspacoFisico tipoEspacoFisico;
	private Endereco endereco;

	private Evento evento;
	private List<Atividade> atividades = new ArrayList<>();
	private List<EspacoFisico> espacoFisicos = new ArrayList<>();

	public EspacoFisico(String descricao, int capacidade, TipoEspacoFisico tipoEspacoFisico) {
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.tipoEspacoFisico = tipoEspacoFisico;
	}

	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public void adicionarEspacosFisicos(EspacoFisico espacoFisico){
		if (!espacoFisicos.contains(espacoFisico)) {
			espacoFisicos.add(espacoFisico);
		}
	}
	
	public void adicionarEvento(Evento evento){
		this.evento = evento;
	}
	
	public void gerarAgenda() {
//		System.out.println("Agenda de atividade por espaço fisico");
		System.out.println("Espaço fisico: " + this.descricao);
		for (Atividade atividade : atividades) {
			System.out.println("\t" + atividade.getNome() + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHoharioInicio().getTime()) + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHoharioTermino().getTime()));

		}
	}

	public String getDescricao() {
		return descricao;
	}

	public List<EspacoFisico> getEspacoFisicos() {
		return espacoFisicos;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}
}