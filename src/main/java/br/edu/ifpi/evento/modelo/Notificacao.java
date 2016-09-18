package br.edu.ifpi.evento.modelo;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.Atividade.Item;
import br.edu.ifpi.evento.Atividade.ItemSimples;
import br.edu.ifpi.evento.constantes.Constante;

public class Notificacao {

	public static void enviarNorificacao(Usuario usuario, String msg) {
		System.out.println(msg + " \n enviada p/ " + usuario.getEmail());
	}

	public static void notificarMudancaEspacoFisico(Atividade atividade) {
		if (atividade instanceof AtividadeCompravel) {
			for (Inscricao inscricao : ((AtividadeCompravel) atividade).getItemSimples().getInscricoes()) {
				Notificacao.enviarNorificacaoAtividade(inscricao.getUsuario(), Constante.MUDOU_ESPACO_FISICO,
						atividade.getNome());
			}
		}
	}

	public static void enviarNorificacaoAtividade(Usuario usuario, String msg, String nomeAtividade) {
		System.out.println("A atividade " + nomeAtividade + " " + msg + " \n enviada p/ " + usuario.getEmail());
	}

}
