package br.edu.ifpi.evento.modelo.evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.edu.ifpi.evento.enums.StatusEvento;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.CupomForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.EventoNaoEstaRecebendoInscricaoException;
import br.edu.ifpi.evento.exceptions.EventoSateliteException;
import br.edu.ifpi.evento.exceptions.InstituicaoException;
import br.edu.ifpi.evento.exceptions.UsuarioRepetidoException;
import br.edu.ifpi.evento.modelo.Instituicao;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.cupom.Cupom;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;
import br.edu.ifpi.evento.observer.Observable;
import br.edu.ifpi.evento.util.Notificacao;
import br.edu.ifpi.evento.util.Validacoes;

@Entity
public class Evento extends Observable {
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoEvento tipoEvento;

	private String nome;

	@OneToMany(mappedBy = "evento")
	private List<Atividade> atividades = new ArrayList<Atividade>();

	@OneToMany(mappedBy = "evento")
	private List<Inscricao> inscricoes = new ArrayList<Inscricao>();

	@OneToMany(mappedBy = "evento")
	private List<Cupom> Cupons = new ArrayList<Cupom>();

	@ManyToMany
	@JoinTable(name = "evento_instituicao", joinColumns = @JoinColumn(name = "evento_id") , inverseJoinColumns = @JoinColumn(name = "instituicao_id") )
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();

	@ManyToOne
	private Evento eventoPai;

	@OneToMany(mappedBy = "eventoPai")
	private List<Evento> eventosSatelites = new ArrayList<Evento>();
	private Calendar dataInicio;
	private Calendar dataFim;

	@Enumerated(EnumType.STRING)
	private StatusEvento status;

	@ManyToOne
	private EspacoFisico espacoFisico;

	@ManyToOne
	private Usuario organizador;

	@ManyToMany
	@JoinTable(name = "evento_equipe", joinColumns = @JoinColumn(name = "evento_id") , inverseJoinColumns = @JoinColumn(name = "equipe_id") )
	private List<Usuario> equipe = new ArrayList<Usuario>();

	private boolean eventoUnico;

	public void verificarDataInicio(Calendar dataInicio) throws DataMenorQueAtualException {
		Calendar now = new GregorianCalendar();

		if (dataInicio.getTimeInMillis() - now.getTimeInMillis() < 0) {
			throw new DataMenorQueAtualException();
		}
	}

	public void adicionarAtividade(Atividade atividade) throws AtividadeException,
			EspacoFisicoComAtividadesConflitantes, AtividadeJaPossuiUmEvento, AtividadeHorarioForaDoPeriodoDoEvento {
		if (atividades.contains(atividade)) {
			throw new AtividadeException();
		}
		Validacoes.verificarHorariosAtividadesDoEvento(dataInicio, dataFim, atividade.getHorarioInicio(),
				atividade.getHorarioTermino());
		atividades.add(atividade);
	}

	public void adicionarInstituicao(Instituicao instituicao) throws InstituicaoException {
		if (instituicoes.contains(instituicao)) {
			throw new InstituicaoException();
		}

		instituicoes.add(instituicao);
		instituicao.addEvento(this);
	}

	public void adicionarUsuarioAEquipe(Usuario usuario) throws UsuarioRepetidoException {
		if (equipe.contains(usuario)) {
			throw new UsuarioRepetidoException();
		}
		equipe.add(usuario);
	}

	public void adicionarIncricao(Inscricao inscricao) throws EventoNaoEstaRecebendoInscricaoException {
		if (!status.equals(StatusEvento.RECEBENDO_INSCRICAO)) {
			throw new EventoNaoEstaRecebendoInscricaoException();
		}
		inscricoes.add(inscricao);
	}

	public void adicionarCupons(Cupom cupom) throws CupomForaDoPeriodoDoEvento {
		Validacoes.verificarHorariosDoCumpomEveto(dataInicio, dataFim, cupom.getValidade());
		Cupons.add(cupom);
		cupom.setEvento(this);
	}

	public void adicionarEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
		espacoFisico.adicionarEvento(this);
	}

	public void adicionarEventoSatelite(Evento eventoSatelite) throws EventoSateliteException,
			EspacoFisicoComAtividadesConflitantes, EventoSateliteHorarioForaDoPeriodoDoEvento {
		if (eventosSatelites.contains(eventoSatelite)) {
			throw new EventoSateliteException();
		}
		verificarPeriodoEventoSatelite(eventoSatelite);
		eventosSatelites.add(eventoSatelite);
	}

	private void verificarPeriodoEventoSatelite(Evento eventoSatelite)
			throws EspacoFisicoComAtividadesConflitantes, EventoSateliteHorarioForaDoPeriodoDoEvento {
		Validacoes.verificarHorariosDoEventoSatelite(dataInicio, dataFim, eventoSatelite.dataInicio,
				eventoSatelite.dataFim);
	}

	public void gerarAgenda() {
		System.out.println("Agenda de atividade por Evento");
		System.out.println("Espaco Fisico Pai: " + this.espacoFisico.getDescricao());
		for (EspacoFisico espacoFisico : espacoFisico.getEspacoFilhos()) {
			espacoFisico.gerarAgenda();
		}
	}

	public boolean isAtivo() {
		Calendar now = new GregorianCalendar();
		return dataFim.getTimeInMillis() < now.getTimeInMillis();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setEventoPai(Evento eventoPai) {
		this.eventoPai = eventoPai;
	}

	public void setDataInicio(Calendar dataInicio) throws DataMenorQueAtualException {
		verificarDataInicio(dataInicio);
		this.dataInicio = dataInicio;
		if (dataInicio != null) {
			setMensagem("O evento " + this.nome + " mudou a data de inicio" + dataInicio.getTime());
			notifyObservers();
		}
	}

	public void setDataFim(Calendar dataFim) throws DataFimMenorQueDataInicioException {
		Validacoes.verificarDataFim(this.dataInicio, dataFim);
		this.dataFim = dataFim;
		if (dataFim != null) {
			setMensagem("O evento " + this.nome + " mudou a data de t�rmino" + dataInicio.getTime());
			notifyObservers();
		}
	}

	public void setStatus(StatusEvento status) {
		this.status = status;
		if (status == StatusEvento.EM_ANDAMENTO) {
			setMensagem("O evento " + this.nome
					+ " ja come�ou, sua inscricao n�o foi paga. Infelizmente vcoc� n�o poder� participar!");
			Notificacao.notificarMudancaDeStatusDoEvento(this);
		}
	}

	public StatusEvento getStatus() {
		return status;
	}

	public void setEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
		if (espacoFisico != null) {
			Notificacao.notificarMudancaEspacoFisicoEvento(this);
		}
	}

	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

	public void setOrganizador(Usuario organizador) {
		this.organizador = organizador;
		if (organizador != null)
			organizador.adicionarevento(this);
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setEventoUnico(boolean eventoUnico) {
		this.eventoUnico = eventoUnico;
	}

	public boolean isEventoUnico() {
		return eventoUnico;
	}

	public List<Instituicao> getInstituicoes() {
		return Collections.unmodifiableList(instituicoes);
	}

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}

	public List<Cupom> getCupons() {
		return Collections.unmodifiableList(Cupons);
	}

	public List<Usuario> getEquipe() {
		return Collections.unmodifiableList(equipe);
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}