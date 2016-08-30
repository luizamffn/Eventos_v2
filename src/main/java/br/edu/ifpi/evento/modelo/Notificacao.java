package br.edu.ifpi.evento.modelo;

import br.edu.ifpi.evento.enums.TipoNotificacao;

public class Notificacao {
	private String msg;
	private TipoNotificacao tipoNotificacao;
	
	public Notificacao(String msg, TipoNotificacao tipoNotificacao) {
		this.msg = msg;
		this.tipoNotificacao = tipoNotificacao;
	}

	public void enviarNotificao() {
		System.out.println(msg + ", " + tipoNotificacao + " mensagem enviada com sucesso!");
	} 
	
}
