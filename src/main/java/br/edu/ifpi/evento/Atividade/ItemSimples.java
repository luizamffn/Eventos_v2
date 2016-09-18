package br.edu.ifpi.evento.Atividade;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.enums.TipoEspacoFisico;
import br.edu.ifpi.evento.modelo.Endereco;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;

@Entity
@DiscriminatorValue(value = "Item_simples")
public class ItemSimples extends Item {

	@OneToOne
	private AtividadeCompravel atividadeCompravel;

	@ManyToOne
	private ItemComposto itemComposto;

	public ItemSimples() {
	}

	public ItemSimples(ItemSimplesBuilder builder) {
		super(builder.id, builder.descricao);
		if (builder.atividadeCompravel != null) {
			this.atividadeCompravel = builder.atividadeCompravel;
			atividadeCompravel.adicionarItem(this);
		}
	}

	public static class ItemSimplesBuilder {
		private final Long id;
		private String descricao;
		private AtividadeCompravel atividadeCompravel;

		public ItemSimplesBuilder(Long id) {
			this.id = id;
		}

		public ItemSimplesBuilder descricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public ItemSimplesBuilder atividadeCompravel(AtividadeCompravel atividadeCompravel) {
			this.atividadeCompravel = atividadeCompravel;
			return this;
		}

		public ItemSimples build() {
			return new ItemSimples(this);
		}
	}

	public AtividadeCompravel getAtividadeCompravel() {
		return atividadeCompravel;
	}

	public void setItemComposto(ItemComposto itemComposto) {
		this.itemComposto = itemComposto;
	}

}
