package br.edu.ifpi.evento.modelo.cupom;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.edu.ifpi.evento.modelo.Atividade.Item;
import br.edu.ifpi.evento.modelo.Atividade.ItemSimples;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;

@Entity
@DiscriminatorValue(value = "DG_50")
public class Desconto_50_Geral extends Cupom {
	
	public Desconto_50_Geral() {
	}
	
	public Desconto_50_Geral(String codigo, Calendar validade) {
		super(codigo, validade);
	}

	@Override
	public double getDesconto(Inscricao inscricao) {
		double desconto = 0;

		for (Item item : inscricao.getItens()) {
			if (item instanceof ItemSimples) {
				desconto += ((ItemSimples) item).getAtividadeCompravel().getValor() * 0.5;
			}
		}
		return desconto;
	}

}
