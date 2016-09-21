package br.edu.ifpi.evento.modelo.cupom;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.Item;
import br.edu.ifpi.evento.modelo.Atividade.ItemSimples;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;

@Entity
@DiscriminatorValue(value = "PL_50")
public class Palestras_50 extends Cupom {

	public Palestras_50() {
	}

	public Palestras_50(String codigo, Calendar validade) {
		super(codigo, validade);
	}

	@Override
	public double getDesconto(Inscricao inscricao) {
		double desconto = 0;

		for (Item item : inscricao.getItens()) {
			if (item instanceof ItemSimples) {
				if (((ItemSimples) item).getAtividadeCompravel()
						.getTipoCompravel() == TipoAtividadeCompravel.PALESTRA) {
					desconto += ((ItemSimples) item).getAtividadeCompravel().getValor() * 0.5;
				}
			}
		}
		// for (Atividade atividade : inscricao.getAtividades()) {
		// if(atividade instanceof AtividadeCompravel){
		// if(((AtividadeCompravel) atividade).getTipoCompravel() ==
		// TipoAtividadeCompravel.PALESTRA){
		// desconto += ((AtividadeCompravel) atividade).getValor() *0.5;
		// }
		// }
		// }

		return desconto;
	}

}
