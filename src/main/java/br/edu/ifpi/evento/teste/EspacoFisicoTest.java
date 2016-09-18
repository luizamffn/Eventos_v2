package br.edu.ifpi.evento.teste;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.evento.Atividade.Atividade;
import br.edu.ifpi.evento.Atividade.AtividadeCompravel;
import br.edu.ifpi.evento.enums.TipoAtividadeCompravel;
import br.edu.ifpi.evento.exceptions.AtividadeException;
import br.edu.ifpi.evento.exceptions.DataFimMenorQueDataInicioException;
import br.edu.ifpi.evento.exceptions.EnderecoEspacoFisicoException;
import br.edu.ifpi.evento.exceptions.EspacoFisicoComAtividadesConflitantes;
import br.edu.ifpi.evento.exceptions.EspacoFisicoPaiException;
import br.edu.ifpi.evento.modelo.Endereco;
import br.edu.ifpi.evento.modelo.EspacoFisico;
import br.edu.ifpi.evento.modelo.Evento;

public class EspacoFisicoTest {
	private EspacoFisico ef1, ef2;
	
	@Before
	public void init() {
		ef1 = new EspacoFisico((long) 1);
		ef2 = new EspacoFisico((long) 2);		
	}

	@Test(expected = EnderecoEspacoFisicoException.class)
	public void nao_aceita_espacos_fisicos_pais_com_mesmo_endereco() throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		Endereco endereco = new Endereco((long) 1);
		ef1.setEspacoPai(ef1);
		ef1.setEndereco(endereco);
		ef2.setEspacoPai(ef2);
		ef2.setEndereco(endereco);
	}
	
	@Test(expected = EspacoFisicoPaiException.class)
	public void somento_o_espacos_fisicos_pai_pode_ter_endereco() throws EspacoFisicoPaiException, EnderecoEspacoFisicoException {
		Endereco endereco = new Endereco((long) 1);
		ef1.setEndereco(endereco);
	}
	
	@Test(expected = EspacoFisicoComAtividadesConflitantes.class)
	public void espacoFisicoNaoPodeConterAtividadesComHorariosConflitantes()
			throws DataFimMenorQueDataInicioException, EspacoFisicoComAtividadesConflitantes, AtividadeException {
		Evento evento = new Evento();
		evento.setEspacoFisico(new EspacoFisico(Long.valueOf(1)));
		EspacoFisico espacoFisico = new EspacoFisico((long) 2);
		
		Calendar dataInicialAt1 = Calendar.getInstance();
		dataInicialAt1.set(2016, 10, 12, 20, 00,00);
		Calendar dataFinalAt1 = Calendar.getInstance();
		dataFinalAt1.set(2016, 10, 12, 22, 00,00);
		
		Calendar dataInicialAt2 = Calendar.getInstance();
		dataInicialAt2.set(2016, 10, 12, 19, 00,00);
		Calendar dataFinalAt2 = Calendar.getInstance();
		dataFinalAt2.set(2016, 10, 12, 20, 00,00);

		Atividade atividade1 = new  AtividadeCompravel((long)1, "at1", evento, espacoFisico, dataInicialAt1, dataFinalAt1, 20.00, TipoAtividadeCompravel.MINICURSO);
		Atividade atividade2 = new  AtividadeCompravel((long)2, "at1", evento, espacoFisico, dataInicialAt2, dataFinalAt2, 20.00, TipoAtividadeCompravel.MINICURSO);

	}

}
