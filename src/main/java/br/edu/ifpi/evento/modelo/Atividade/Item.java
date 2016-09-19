package br.edu.ifpi.evento.modelo.Atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import br.edu.ifpi.evento.modelo.inscricao.Inscricao;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Item {
	
	@Id
	@GeneratedValue
	protected Long id;
	protected String descricao;
	
	@Column(insertable = false, updatable = false)
	protected String tipo;
	
	@ManyToMany(mappedBy="itens")
	protected List<Inscricao> inscricoes = new ArrayList<Inscricao>();
	
	public Item() {
		
	}
	
	public Item(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public void adicionarInscricao(Inscricao inscricao) {
		inscricoes.add(inscricao);
	}
	
	public void listaInscritos() {
		System.out.println("Nome dos inscritos:");
		for (Inscricao inscricao : inscricoes) {
			System.out.println("\t" + inscricao.getUsuario().getPessoa().getNome());
		}
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
