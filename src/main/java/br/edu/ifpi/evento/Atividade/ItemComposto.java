package br.edu.ifpi.evento.Atividade;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "Item_Composto")
public class ItemComposto extends Item{
	private double precoComDesconto;
	double desconto;
	
	@OneToMany(mappedBy="itemComposto")
	List<ItemSimples> itensSimples;
	
	public ItemComposto() {
	}
	
	public ItemComposto(Long id, String descricao) {
		super(id, descricao);
	}
	
	public void calcularDesconto(double porcentagem) {
		double precoTotal =0 ;
		for (ItemSimples itemSimples : itensSimples) {
			precoTotal += itemSimples.getAtividadeCompravel().getValor();
		}
		
		desconto = precoTotal * desconto;
		
		precoComDesconto = precoTotal - desconto;
	}

	public double getPrecoComDesconto() {
		return precoComDesconto;
	}

	public List<ItemSimples> getItensSimples() {
		return itensSimples;
	}

}
