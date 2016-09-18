package br.edu.ifpi.evento.mensagem;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GerarMensagem {
	public static void mensagemSucesso(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	public static void mensagensAlerta(String summary) {
		String[] mensagens = summary.split(";");
		for (String mensagem : mensagens) {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									mensagem, null));
		}

	}

}
