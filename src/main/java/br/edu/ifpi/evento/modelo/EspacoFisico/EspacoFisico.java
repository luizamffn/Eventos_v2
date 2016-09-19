package br.edu.ifpi.evento.modelo.EspacoFisico;

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

import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.exceptions.EnderecoEspacoFisicoException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EspacoFisicoPaiException;
import br.edu.ifpi.evento.modelo.Endereco;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.util.Converter;
import br.edu.ifpi.evento.util.Validacoes;

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

	@OneToMany(mappedBy = "espacoFisico")
	private List<Evento> eventos;

	@OneToMany(mappedBy = "espacoFisico")
	private List<Atividade> atividades = new ArrayList<Atividade>();

	@ManyToOne
	private EspacoFisico espacoPai;

	@OneToMany(mappedBy = "espacoPai")
	private List<EspacoFisico> espacoFilhos = new ArrayList<EspacoFisico>();

	public void adicionarAtividade(Atividade atividade) throws EspacoFisicoComAtividadesConflitantes {
		for (Atividade a : atividades) {
			Validacoes.verificarHorariosAtividades(a.getHorarioInicio(), a.getHorarioTermino(), atividade.getHorarioInicio(),
					atividade.getHorarioTermino());
		}
		atividades.add(atividade);
	}

	public void adicionarEspacosFisicos(EspacoFisico espacoFisico) {
		if (!espacoFilhos.contains(espacoFisico)) {
			espacoFilhos.add(espacoFisico);
		}
	}

	public void adicionarEvento(Evento evento) {
		eventos.add(evento);
	}

	public void gerarAgenda() {
		// System.out.println("Agenda de atividade por espaço fisico");
		System.out.println("Espaço fisico: " + this.descricao);
		for (Atividade atividade : atividades) {
			System.out.println("\t" + atividade.getNome() + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHorarioInicio().getTime()) + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHorarioTermino().getTime()) + "\t"
					+ Converter.datetimeToStr(atividade.getHorarioInicio().getTime()) + " - "
					+ Converter.datetimeToStr(atividade.getHorarioTermino().getTime()));

		}
	}

	public void listaInscritos() {
		System.out.println("Espaco fisico: " + this.descricao);
		for (Atividade atividade : atividades) {
			if (atividade instanceof AtividadeCompravel) {
				System.out.println("Atividade: " + atividade.getNome());
				((AtividadeCompravel) atividade).getItemSimples().listaInscritos();
			}
		}
	}

	public void limparEndereco() {
		this.endereco.limparEspacoFisico();
		this.endereco = null;
	}

	public void setEndereco(Endereco endereco) throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		if (!this.equals(espacoPai)) {
			throw new EspacoFisicoPaiException();
		}

		endereco.setEspacoFisico(this);
		this.endereco = endereco;
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

	public EspacoFisico getEspacoPai() {
		return espacoPai;
	}

	public void setEspacoPai(EspacoFisico espacoPai) {
		this.espacoPai = espacoPai;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public void setTipoEspacoFisico(TipoEspacoFisico tipoEspacoFisico) {
		this.tipoEspacoFisico = tipoEspacoFisico;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public void setEspacoFilhos(List<EspacoFisico> espacoFilhos) {
		this.espacoFilhos = espacoFilhos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspacoFisico other = (EspacoFisico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}