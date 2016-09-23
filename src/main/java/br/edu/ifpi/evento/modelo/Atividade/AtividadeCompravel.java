package br.edu.ifpi.evento.modelo.Atividade;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.modelo.Item.Item;
import br.edu.ifpi.evento.modelo.Item.ItemSimples;

@Entity
@DiscriminatorValue(value = "At_compravel")
public class AtividadeCompravel extends Atividade {
	private Double valor;

	@Enumerated(EnumType.STRING)
	private TipoAtividadeCompravel tipoCompravel;
	
	@OneToOne
	private ItemSimples itemSimples;

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setTipoCompravel(TipoAtividadeCompravel tipoCompravel) {
		this.tipoCompravel = tipoCompravel;
	}

	public TipoAtividadeCompravel getTipoCompravel() {
		return tipoCompravel;
	}

	public void setItemSimples(ItemSimples itemSimples) {
		this.itemSimples = itemSimples;
	}
	
	public Item getItemSimples() {
		return itemSimples;
	}

	public int compareTo(Atividade o) {
		if(this.getHorarioInicio().getTimeInMillis() < o.getHorarioInicio().getTimeInMillis()){
			return -1;
		}else if(this.getHorarioInicio().getTimeInMillis() > o.getHorarioInicio().getTimeInMillis()){
			return 1;
		}
		return 0;
	}

}
