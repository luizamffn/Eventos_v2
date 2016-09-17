package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.util.Converter;

@Entity
public class EspacoFisico {
	
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	private int capacidade;
	
	@Enumerated(EnumType.STRING)
	private TipoEspacoFisico tipoEspacoFisico;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToMany(mappedBy="espacoFisico")
	private List<Evento> eventos;
	
	@OneToMany(mappedBy="espacoFisico")
	private List<Atividade> atividades = new ArrayList<Atividade>();
	
	@ManyToOne
	private EspacoFisico espacoPai;
	
	@OneToMany(mappedBy="espacoPai")
	private List<EspacoFisico> espacoFilhos = new ArrayList<EspacoFisico>();
	
	public EspacoFisico() {

	}
	
	public EspacoFisico(String descricao, int capacidade, TipoEspacoFisico tipoEspacoFisico) {
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.tipoEspacoFisico = tipoEspacoFisico;
	}

	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public void adicionarEspacosFisicos(EspacoFisico espacoFisico){
		if (!espacoFilhos.contains(espacoFisico)) {
			espacoFilhos.add(espacoFisico);
		}
	}
	
	public void adicionarEvento(Evento evento){
		eventos.add(evento);
	}
	
	public void gerarAgenda() {
//		System.out.println("Agenda de atividade por espaço fisico");
		System.out.println("Espaço fisico: " + this.descricao);
		for (Atividade atividade : atividades) {
			System.out.println("\t" + atividade.getNome() + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHoharioInicio().getTime()) + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHoharioTermino().getTime()) + "\t"
					+ Converter.datetimeToStr(atividade.getHoharioInicio().getTime()) + " - "
					+ Converter.datetimeToStr(atividade.getHoharioTermino().getTime()));

		}
	}

	public void listaInscritos() {
		System.out.println("Espaco fisico: " + this.descricao);
		for (Atividade atividade : atividades) {
			if(atividade instanceof AtividadeCompravel){
				System.out.println("Atividade: " + atividade.getNome());
				((AtividadeCompravel) atividade).getItemSimples().listaInscritos();
			}
		}
	}
	
	public String getDescricao() {
		return descricao;
	}

	public List<EspacoFisico> getEspacoFilhos() {
		return espacoFilhos;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}
}