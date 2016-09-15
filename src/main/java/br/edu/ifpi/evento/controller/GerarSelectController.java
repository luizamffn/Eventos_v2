package br.edu.ifpi.evento.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpi.evento.enums.TipoUsuario;

@ManagedBean
@ViewScoped
public class GerarSelectController {
	
	public List<TipoUsuario> getTipoUsuario() {
		return Arrays.asList(TipoUsuario.PARTICIPANTE,
								TipoUsuario.ORGANIZADOR);
	}
}
