package br.edu.ifpi.evento.testeAplicacao;

import java.util.Calendar;

import br.edu.ifpi.evento.dao.impl.AtividadeDAOImpl;
import br.edu.ifpi.evento.dao.impl.EspacoFisicoDAOImpl;
import br.edu.ifpi.evento.dao.impl.EventoDAOImpl;
import br.edu.ifpi.evento.dao.impl.ItemDAOImpl;
import br.edu.ifpi.evento.dao.impl.PessoaDAOImpl;
import br.edu.ifpi.evento.dao.impl.UsuarioDAOImpl;
import br.edu.ifpi.evento.enums.Sexo;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoEvento;
import br.edu.ifpi.evento.enums.TipoUsuario;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.AtividadeHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.AtividadeJaPossuiUmEvento;
import br.edu.ifpi.evento.exceptions.AtividadeNaoEstaNoEventoException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.DataMenorQueAtualException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EventoNaoEstaRecebendoInscricaoException;
import br.edu.ifpi.evento.exceptions.EventoSateliteHorarioForaDoPeriodoDoEvento;
import br.edu.ifpi.evento.exceptions.InscricaoPagaException;
import br.edu.ifpi.evento.modelo.Pessoa;
import br.edu.ifpi.evento.modelo.Usuario;
import br.edu.ifpi.evento.modelo.Atividade.Atividade;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.modelo.Atividade.AtividadeCompravelBuilder;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisico;
import br.edu.ifpi.evento.modelo.EspacoFisico.EspacoFisicoBuilder;
import br.edu.ifpi.evento.modelo.Item.Item;
import br.edu.ifpi.evento.modelo.Item.ItemSimples;
import br.edu.ifpi.evento.modelo.Item.ItemSimplesBuilder;
import br.edu.ifpi.evento.modelo.evento.Evento;
import br.edu.ifpi.evento.modelo.evento.EventoBuilder;
import br.edu.ifpi.evento.modelo.inscricao.Inscricao;
import br.edu.ifpi.evento.modelo.inscricao.InscricaoBuilder;

public class EventoAplicacao {
	static PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(Pessoa.class);
	static UsuarioDAOImpl UsuarioDAO = new UsuarioDAOImpl(Usuario.class);
	static EventoDAOImpl eventoDAO = new EventoDAOImpl(Evento.class);
	static EspacoFisicoDAOImpl espacoFisicoDAO = new EspacoFisicoDAOImpl(EspacoFisico.class);
	static AtividadeDAOImpl atividadeDAO = new AtividadeDAOImpl(Atividade.class);
	static ItemDAOImpl itemDao= new ItemDAOImpl(Item.class);

	public static void main(String[] args) throws DataFimMenorQueDataInicioException, DataMenorQueAtualException,
			AtividadeJaPossuiUmEvento, AtividadeException, EspacoFisicoComAtividadesConflitantes,
			AtividadeHorarioForaDoPeriodoDoEvento, EventoSateliteHorarioForaDoPeriodoDoEvento, InscricaoPagaException, AtividadeNaoEstaNoEventoException, EventoNaoEstaRecebendoInscricaoException {
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(2016, 10, 10, 10, 00);
		Calendar dataFim = Calendar.getInstance();
		dataFim.set(2016, 10, 20, 22, 00);

		Pessoa joao = new Pessoa("Joao da silva", 1245, Sexo.M);
		joao = pessoaDAO.save(joao);

		Usuario organizador = new Usuario("joao56", "1234", joao, TipoUsuario.ORGANIZADOR);
		organizador = UsuarioDAO.save(organizador);
		
		Pessoa maria = new Pessoa("Maria", 1245, Sexo.M);
		joao = pessoaDAO.save(maria);
		
		Usuario participante = new Usuario("maria33", "1234", joao, TipoUsuario.PARTICIPANTE);
		organizador = UsuarioDAO.save(participante);

		EspacoFisico espacoFisico = EspacoFisicoBuilder.builder()
				.capacidade(30)
				.descricao("Predio A")
				.getEspacoFisico();
		espacoFisico = espacoFisicoDAO.save(espacoFisico);
		
		Evento evento = EventoBuilder.builder().nome("Tecnologia e suas inovações").dataInicio(dataInicio)
				.dataFim(dataFim)
				.tipoEvento(TipoEvento.SEMANA_CIENTIFICA)
				.getEvento();
		evento = eventoDAO.save(evento);
		
		evento.setEventoPai(evento);
		evento.setOrganizador(organizador);
		evento.setEspacoFisico(espacoFisico);
		evento = eventoDAO.save(evento);
		
		dataInicio.set(2016, 10, 11, 10, 00);
		dataFim.set(2016, 10, 11, 12, 00);

		AtividadeCompravel atividade  = AtividadeCompravelBuilder.builder()
				.nome("Nocões de Python")
				.horarioInicio(dataInicio)
				.horarioFim(dataFim)
				.tipo(TipoAtividadeCompravel.MINICURSO)
				.valor(20)
				.evento(evento)
				.getAtidadeCompravel();
		atividade = (AtividadeCompravel) atividadeDAO.save(atividade);
		
		ItemSimples itemSimples = ItemSimplesBuilder.builder()
				.descricao("Minicurso python")
				.getItemSimples();
		
		itemSimples = (ItemSimples) itemDao.save(itemSimples);
		
		itemSimples.setAtividadeCompravel(atividade);
		itemSimples = (ItemSimples) itemDao.save(itemSimples);
		
		atividade.setItemSimples(itemSimples);
		atividade = (AtividadeCompravel) atividadeDAO.save(atividade);
		
		Inscricao inscricao = InscricaoBuilder.builder()
				.evento(evento)
				.usuario(participante)
				.getInscricao();
				
	}
}
