package br.edu.ifpi.evento.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	private String usuario;
	private String senha;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@OneToOne
    @JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@OneToMany(mappedBy="organizador")
	private List<Evento> eventosOrganizados = new ArrayList<Evento>();
	
	@ManyToMany(mappedBy="equipe")
	private List<Evento> eventosdaEquipe;
	
	@OneToMany(mappedBy="usuario")
	private List<Inscricao> inscricoes = new ArrayList<Inscricao>();
	
	public Usuario() {
	}
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", email=" + email + ", tipoUsuario="
				+ tipoUsuario + ", pessoa=" + pessoa + ", eventosOrganizados=" + eventosOrganizados
				+ ", eventosdaEquipe=" + eventosdaEquipe + ", inscricoes=" + inscricoes + "]";
	}
	
	
	
}
