package br.edu.ifpi.evento.modelo;

import br.edu.ifpi.evento.enums.TipoUsuario;

public class Usuario {
	private Long id;
//	private String nome;
	private String usuario;
	private String senha;
	
	private Pessoa pessoa;
	private TipoUsuario tipoUsuario;
	
	
	public Usuario(String usuario, String senha, Pessoa pessoa, TipoUsuario tipoUsuario) {
		this.usuario = usuario;
		this.senha = senha;
		this.pessoa = pessoa;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
