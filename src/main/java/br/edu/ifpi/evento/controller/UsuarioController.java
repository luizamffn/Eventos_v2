package br.edu.ifpi.evento.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpi.evento.constantes.Mensagem;
import br.edu.ifpi.evento.dao.impl.UsuarioDAOImpl;
import br.edu.ifpi.evento.mensagem.GerarMensagem;
import br.edu.ifpi.evento.modelo.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioController {
	UsuarioDAOImpl usuarioDAO; 

	private Usuario usuario;

	public UsuarioController() {
		usuarioDAO = new UsuarioDAOImpl(Usuario.class);
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			System.out.println("esta salvando");
			System.out.println(usuario);
			Usuario u = usuarioDAO.save(usuario);
			System.out.println(u.getId());
			GerarMensagem.mensagemSucesso(Mensagem.OPERACAO_SUCESSO, null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("nao salvou");
			System.out.println(e.getMessage());
			GerarMensagem.mensagensAlerta(e.getMessage());
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
