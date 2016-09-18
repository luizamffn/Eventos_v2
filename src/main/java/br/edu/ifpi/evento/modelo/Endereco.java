package br.edu.ifpi.evento.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.edu.ifpi.evento.exceptions.EnderecoEspacoFisicoException;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue
	private Long id;
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	
	@OneToOne
	private EspacoFisico espacoFisico;
	
	public void limparEspacoFisico() {
		this.espacoFisico = null;
	}
	
	public Endereco() {
	}

	public Endereco(Long id) {
		this.id = id;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

	public void setEspacoFisico(EspacoFisico espacoFisico) throws EnderecoEspacoFisicoException {
		if(this.espacoFisico != null){
			throw new EnderecoEspacoFisicoException();
		}
		this.espacoFisico = espacoFisico;
	}
	
	
}
