package br.edu.ifpi.evento.modelo.Item;

import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;

public class ItemSimplesBuilder {
	ItemSimples itemSimples;
//	private Long id;
//	private String descricao;
//	private AtividadeCompravel atividadeCompravel;
	
	public ItemSimplesBuilder() {
		itemSimples = new ItemSimples();
	}
	
	public static ItemSimplesBuilder builder() {
		return new ItemSimplesBuilder();
	}

	public ItemSimplesBuilder id(Long id) {
		this.itemSimples.setId(id);
		return this;
	}
	
	public ItemSimplesBuilder descricao(String descricao) {
		this.itemSimples.setDescricao(descricao);
		return this;
	}

	public ItemSimplesBuilder atividadeCompravel(AtividadeCompravel atividadeCompravel) {
		this.itemSimples.setAtividadeCompravel(atividadeCompravel);
		atividadeCompravel.setItemSimples(itemSimples);
		return this;
	}
	
	public ItemSimples getItemSimples() {
		return itemSimples;
	}

}

