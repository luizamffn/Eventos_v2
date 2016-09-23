package br.edu.ifpi.evento.util;

import br.edu.ifpi.evento.constantes.Constante;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;

public class Notificacao {

	public static void notificarMudancaEspacoFisico(Atividade atividade) {
		if (atividade instanceof AtividadeCompravel) {
			if (((AtividadeCompravel) atividade).getItemSimples() != null) {
				for (Inscricao inscricao : ((AtividadeCompravel) atividade).getItemSimples().getInscricoes()) {
					atividade.setMensagem((inscricao.getUsuario().getPessoa().getNome() + ", A " + atividade.getNome()
							+ Constante.MUDOU_ESPACO_FISICO));
					atividade.notifyUmObserver(inscricao.getUsuario());
				}
			}
		}
	}

	public static void notificarMudancaDeStatusDoEvento(Evento evento) {
		for (Inscricao insc : evento.getInscricoes()) {
			if (insc.getPagamento() == null) {
				evento.notifyUmObserver(insc.getUsuario());
				evento.delObserver(insc.getUsuario());
			}
		}
	}

	public static void notificarMudancaEspacoFisicoEvento(Evento evento) {
		for (Inscricao insc : evento.getInscricoes()) {
			evento.setMensagem((insc.getUsuario().getPessoa().getNome() + ", O evento: " + evento.getNome()
					+ Constante.MUDOU_ESPACO_FISICO));
			evento.notifyUmObserver(insc.getUsuario());
		}
	}

}
