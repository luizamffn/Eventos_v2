package br.edu.ifpi.evento.Atividade;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "Item_simples")
public class ItemSimples extends Item{
	
	@OneToOne
	private AtividadeCompravel atividadeCompravel;
	
	@ManyToOne
	private ItemComposto itemComposto;
	
	public ItemSimples() {
	}
	
	public ItemSimples(Long id, String descricao, AtividadeCompravel atividadeCompravel) {
		super(id, descricao);
		this.atividadeCompravel = atividadeCompravel;
		atividadeCompravel.adicionarItem(this);
	}

	public AtividadeCompravel getAtividadeCompravel() {
		return atividadeCompravel;
	}
	
}
