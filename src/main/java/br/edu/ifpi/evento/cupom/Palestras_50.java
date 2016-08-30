package br.edu.ifpi.evento.cupom;

import java.util.Calendar;

import br.edu.ifpi.evento.enums.TipoAtividade;
import br.edu.ifpi.evento.modelo.Atividade;
import br.edu.ifpi.evento.modelo.Inscricao;

public class Palestras_50 extends Cupom{

	public Palestras_50(boolean ativo, Calendar validade) {
		super(ativo, validade);
	}

	@Override
	public double getDesconto(Inscricao inscricao) {
		double desconto = 0;
		for (Atividade atividade : inscricao.getAtividades()) {
			if(atividade.getTipoAtividade() == TipoAtividade.PALESTRA){
				desconto += atividade.getValor() *0.5;
			}
		}
		return desconto;
	}
}
