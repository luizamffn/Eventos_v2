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
import br.edu.ifpi.evento.exceptions.EnderecoEspacoFisicoException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EspacoFisicoPaiException;
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

	@OneToMany(mappedBy = "espacoFisico")
	private List<Evento> eventos;

	@OneToMany(mappedBy = "espacoFisico")
	private List<Atividade> atividades = new ArrayList<Atividade>();

	@ManyToOne
	private EspacoFisico espacoPai;

	@OneToMany(mappedBy = "espacoPai")
	private List<EspacoFisico> espacoFilhos = new ArrayList<EspacoFisico>();

	public EspacoFisico() {

	}

	public EspacoFisico(Long id) {
		this.id = id;
	}

	public EspacoFisico(String descricao, int capacidade, TipoEspacoFisico tipoEspacoFisico) {
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.tipoEspacoFisico = tipoEspacoFisico;
	}

	public void adicionarAtividade(Atividade atividade) throws EspacoFisicoComAtividadesConflitantes{
		System.out.println("verificada " + atividade.getHoharioInicio().getTimeInMillis() );
			for (Atividade a : atividades) {
				System.out.println("atividade innicio " + a.getHoharioInicio().getTimeInMillis() );
				System.out.println("atividade fim " + a.getHoharioTermino().getTimeInMillis());
				if (a.getHoharioInicio().getTimeInMillis() <= atividade.getHoharioInicio().getTimeInMillis()
						&& a.getHoharioTermino().getTimeInMillis() >= atividade.getHoharioInicio().getTimeInMillis()
						|| a.getHoharioInicio().getTimeInMillis() <= atividade.getHoharioTermino().getTimeInMillis()
								&& a.getHoharioTermino().getTimeInMillis() >= atividade.getHoharioTermino()
										.getTimeInMillis()
						) {
					throw new EspacoFisicoComAtividadesConflitantes();
				}
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
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHoharioInicio().getTime()) + " - "
					+ Converter.dateToStrFormatoBrasileiro(atividade.getHoharioTermino().getTime()) + "\t"
					+ Converter.datetimeToStr(atividade.getHoharioInicio().getTime()) + " - "
					+ Converter.datetimeToStr(atividade.getHoharioTermino().getTime()));

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

	public String getDescricao() {
		return descricao;
	}

	public List<EspacoFisico> getEspacoFilhos() {
		return espacoFilhos;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}


	public void setEndereco(Endereco endereco) throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		if (!this.equals(espacoPai)) {
			throw new EspacoFisicoPaiException();
		}

		endereco.setEspacoFisico(this);
		this.endereco = endereco;
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

	public EspacoFisico getEspacoPai() {
		return espacoPai;
	}

	public void setEspacoPai(EspacoFisico espacoPai) {
		this.espacoPai = espacoPai;
	}
}