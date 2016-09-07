package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.evento.enums.TipoUsuario;

public class Usuario {
	private Long id;
//	private String nome;
	private String usuario;
	private String senha;
	
	private Pessoa pessoa;
	private TipoUsuario tipoUsuario;
	private List<Evento> eventosOrganizados = new ArrayList<>();
	private List<Evento> eventosdaEquipe;
	private List<Inscricao> inscricoes = new ArrayList<>();
	
	public Usuario(String usuario, String senha, Pessoa pessoa, TipoUsuario tipoUsuario) {
		this.usuario = usuario;
		this.senha = senha;
		this.pessoa = pessoa;
		this.tipoUsuario = tipoUsuario;
	}
	
	public void adicionarInscricao(Inscricao inscricao) {
		inscricoes.add(inscricao);
	}
	
	public void adicionarevento(Evento evento) {
		eventosOrganizados.add(evento);
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

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public List<Evento> getEventosOrganizados() {
		return Collections.unmodifiableList(eventosOrganizados);
	}

	public List<Evento> getEventosdaEquipe() {
		return Collections.unmodifiableList(eventosdaEquipe);
	}
	
}
