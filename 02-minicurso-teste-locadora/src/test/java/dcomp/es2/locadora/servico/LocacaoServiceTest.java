package dcomp.es2.locadora.servico;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import dcomp.es2.locadora.modelo.Filme;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.modelo.Usuario;
import dcomp.es2.locadora.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void testaUmaLocacao() {
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Fulano");
		Filme filme = new Filme("Batman o Retorno", 2, 5.0);

		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificação

		Assert.assertThat(locacao.getValor(), is(equalTo(5.0)));
		Assert.assertThat(locacao.getValor(), is(not(6.0)));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), LocalDate.now() ), is(true) );
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.amanha()), is(true) );

		/*
		 * Assert.assertEquals(5, locacao.getValor(), 0.0001 ); Assert.assertTrue(
		 * DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()) );
		 * Assert.assertTrue( DataUtils.isMesmaData(locacao.getDataRetorno(),
		 * DataUtils.amanha() ) );
		 */

		/*
		 * Assert.assertTrue( locacao.getValor() == 5.0 ); Assert.assertTrue(
		 * DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()) );
		 * Assert.assertTrue( DataUtils.isMesmaData(locacao.getDataRetorno(),
		 * DataUtils.amanha() ) );
		 */
	}

	@Rule
	public ErrorCollector erros = new ErrorCollector();

	@Test
	public void testaUmaLocacaoComErroCollector() {
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Fulano");
		Filme filme = new Filme("Batman o Retorno", 2, 5.0);

		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificação

		erros.checkThat(locacao.getValor(), is(equalTo(5.0)));
		erros.checkThat(locacao.getValor(), is(not(6.0)));
		
		erros.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), LocalDate.now()), is(true));
		erros.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.amanha()), is(true));
		
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy" );
		
	}

	@Test(expected=RuntimeException.class)
	public void naoDeveAlugarFilmeSemEstoque() {
		Usuario usuario = new Usuario("Fulano");
		Filme filme = new Filme("Batman o Retorno", 0, 5.0);

		LocacaoService service = new LocacaoService();
		service.alugarFilme(usuario, filme);

	}

	@Rule
	public ExpectedException excecao = ExpectedException.none();
	
	@Test
	public void naoDeveAlugarFilmeSemEstoque2() {
		Usuario usuario = new Usuario("Fulano");
		Filme filme = new Filme("Batman o Retorno", 0, 5.0);

		excecao.expect(RuntimeException.class );
		excecao.expectMessage("Sem Estoque" );

		LocacaoService service = new LocacaoService();
		service.alugarFilme(usuario, filme);
		

	}
	
	

}