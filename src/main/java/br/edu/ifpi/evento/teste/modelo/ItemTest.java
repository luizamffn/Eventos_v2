package br.edu.ifpi.evento.teste.modelo;

import org.junit.Test;

import br.edu.ifpi.evento.exceptions.ItemSimplesJaAdicionadoException;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Item.ItemComposto;
import br.edu.ifpi.evento.modelo.Item.ItemSimples;
import br.edu.ifpi.evento.modelo.Item.ItemSimplesBuilder;

public class ItemTest {

	@Test(expected = ItemSimplesJaAdicionadoException.class)
	public void Item_composto_nao_pode_ter_item_simples_repetidos() throws ItemSimplesJaAdicionadoException {
		ItemComposto itemComposto = new ItemComposto((long) 1, "Kit minicurso");
		AtividadeCompravel atividadeCompravel = new AtividadeCompravel();
		atividadeCompravel.setValor(20.00);
		ItemSimples itemSimples = ItemSimplesBuilder.builder().id((long) 1).atividadeCompravel(atividadeCompravel)
				.getItemSimples();

		itemComposto.addItemSimples(itemSimples);
		itemComposto.addItemSimples(itemSimples);
	}

}
