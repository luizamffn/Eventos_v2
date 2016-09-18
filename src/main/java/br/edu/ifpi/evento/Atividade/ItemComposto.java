package br.edu.ifpi.evento.Atividade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.edu.ifpi.evento.exceptions.ItemSimplesJaAdicionadoException;

@Entity
@DiscriminatorValue(value = "Item_Composto")
public class ItemComposto extends Item{
	private double precoTotal;
	private double precoComDesconto;
	double desconto;
	
	@OneToMany(mappedBy="itemComposto")
	List<ItemSimples> itensSimples = new ArrayList<ItemSimples>();
	
	public ItemComposto() {
	}
	
	public ItemComposto(Long id, String descricao) {
		super(id, descricao);
	}
	
	public void addItemSimples(ItemSimples itemSimples) throws ItemSimplesJaAdicionadoException {
		if(itensSimples.contains(itemSimples)){
			throw new ItemSimplesJaAdicionadoException();
		}
		itensSimples.add(itemSimples);
		itemSimples.setItemComposto(this);
		calcularPrecoTotal();
	}
	
	public void calcularDesconto(double porcentagem) {
		desconto = precoTotal * porcentagem;
		precoComDesconto = precoTotal - desconto;
	}

	public double getPrecoComDesconto() {
		return precoComDesconto;
	}

	public List<ItemSimples> getItensSimples() {
		return itensSimples;
	}

	public void calcularPrecoTotal() {
		this.precoTotal= 0;
		for (ItemSimples itemSimples : itensSimples) {
			precoTotal += itemSimples.getAtividadeCompravel().getValor();
		}
	}

}
