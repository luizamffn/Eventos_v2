package br.edu.ifpi.evento.testeAplicacao;

import br.edu.ifpi.evento.dao.impl.UsuarioDAOImpl;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.modelo.Usuario;

public class TesteUsuario {
	static UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(Usuario.class);
	public static void main(String[] args) {
		Usuario usuario;
		
		usuario = new Usuario("lmffn","123" , null, TipoUsuario.ORGANIZADOR);
		
		Usuario u = usuarioDAO.save(usuario);
		System.out.println("ID" + u.getId());
	}
	
}
