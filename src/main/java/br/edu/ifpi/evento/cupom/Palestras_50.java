package br.edu.ifpi.evento.cupom;

import java.util.Calendar;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.modelo.Inscricao;

public class Palestras_50 extends Cupom{

	public Palestras_50(String codigo, Calendar validade) {
		super(codigo, validade);
	}

	@Override
	public double getDesconto(Inscricao inscricao) {
		double desconto = 0;
		if(isAtivo()){
			for (Atividade atividade : inscricao.getAtividades()) {
				if(atividade instanceof AtividadeCompravel){
					if(((AtividadeCompravel) atividade).getTipo() == TipoAtividadeCompravel.PALESTRA){
						desconto += ((AtividadeCompravel) atividade).getValor() *0.5;
					}
				}
			}
		}
		return desconto;
	}
			
}
