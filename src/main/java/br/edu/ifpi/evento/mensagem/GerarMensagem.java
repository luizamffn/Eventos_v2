package br.edu.ifpi.evento.mensagem;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GerarMensagem {

	/**
	 * 
	 * Metodo que retorna mensagem de sucesso na .jsf
	 * 
	 * @param summary
	 *            - O corpo da mensagem
	 * @param detail
	 *            - O Titulo da mensagem
	 */
	public static void mensagemSucesso(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	/**
	 * 
	 * Metodo que retorna mensagem de sucesso na .jsf
	 * 
	 * @param summary
	 *            - O corpo da mensagem
	 * @param detail
	 *            - O Titulo da mensagem
	 */
	public static void mensagensErro(String summary) {
		String[] mensagens = summary.split(";");
		for (String mensagem : mensagens) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
							null));
		}

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

	/**
	 * 
	 * Metodo que retorna mensagem de sucesso na .jsf
	 * 
	 * @param summary
	 *            - O corpo da mensagem
	 * @param cliente
	 *            - id do item onde será mostrada a mensagem
	 */
	public static void mensagensErro(String summary, String cliente) {
		String[] mensagens = summary.split(";");
		for (String mensagem : mensagens) {
			FacesContext.getCurrentInstance().addMessage(
					cliente,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
							null));
		}

	}

	/**
	 * 
	 * Metodo que retorna mensagem de erro na .jsf
	 * 
	 * @param summary
	 *            - O corpo da mensagem
	 * @param detail
	 *            - O Titulo da mensagem
	 */
	public static void mensagemErro(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}

	/**
	 * 
	 * Metodo que retorna mensagem de alerta na .jsf
	 * 
	 * @param summary
	 *            - O corpo da mensagem
	 * @param detail
	 *            - O Titulo da mensagem
	 */
	public static void mensagemAlerta(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
	}

	/**
	 * 
	 * Metodo que retorna mensagem de fatal na .jsf utilizar na catch
	 * 
	 * @param summary
	 *            - O corpo da mensagem
	 * @param detail
	 *            - O Titulo da mensagem
	 */
	public static void mensagemFatal(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail));
	}

}
