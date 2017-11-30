package dcomp.es2.locadora.servico;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import dcomp.es2.locadora.modelo.Filme;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.modelo.Usuario;
import dcomp.es2.locadora.utils.DataUtils;

public class LocacaoServiceTest {
	
	
	private LocacaoService service;
	private Usuario usuario;


	@Before
	public void setup() {
		service = new LocacaoService();
		usuario = new Usuario("Fulano");
	}


	@Test
	public void testaUmaLocacao() {
		
		Assume.assumeFalse( LocalDate.now().getDayOfWeek() ==  DayOfWeek.SATURDAY );

		
		// cenário
		Filme filme = new Filme("Batman o Retorno", 2, 5.0);

		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificação

		Assert.assertThat(locacao.getValor(), is(equalTo(5.0)));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), LocalDate.now() ), is(true) );
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.amanha()), is(true) );
	
	}

	
	@Test(expected=RuntimeException.class)
	public void naoDeveAlugarFilmeSemEstoque() {
		
		Filme filme = new Filme("Filme 1", 0, 5.0);
		service.alugarFilme(usuario, filme);

	}

	
	@Test
	public void deveAplicarDesconto10PctNoSegundoFilme() {
		
		// cenário
		List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 5, 4.0),
		                                    new Filme("Filme 2", 5, 4.0) );                
		
		//ação
		Locacao locacao = service.alugarFilmes(usuario, filmes);
		
		
		// verificação
		// 4 + 4*90% = 4 + 3.60 = 7.60
		Assert.assertThat(locacao.getValor(), is(7.60d) );		
		
	}
	
	@Test
	public void deveAplicarDesconto30PctNoTerceiroFilme() {
		
		// cenário
		List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 5, 4.0),
                                            new Filme("Filme 2", 2, 4.0),
                                            new Filme("Filme 3", 3, 4.0) );                
		
		//ação
		Locacao locacao = service.alugarFilmes(usuario, filmes);
		
		
		// verificação
		// 4 + 4*90% + 4 * 0.70 = 4 + 3.60 + 2.80 = 10.40d
		
		Assert.assertNotNull(locacao);		
		Assert.assertEquals(10.40d, locacao.getValor(), 0.00001);		
		
	}
	
	@Test
	public void deveAplicarDesconto50PctNoQuartoFilme() {
		
		// cenário
		List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 5, 4.0),
                                            new Filme("Filme 2", 2, 4.0),
                                            new Filme("Filme 3", 3, 4.0),                
											new Filme("Filme 4", 3, 4.0) );                
		
		//ação
		Locacao locacao = service.alugarFilmes(usuario, filmes);
		
		
		// verificação
		// 4 + 4*90% + 4 * 0.70 = 4 + 3.60 + 2.80 + 2.0 = 12.40
		
		Assert.assertNotNull(locacao);		
		Assert.assertEquals(12.40d, locacao.getValor(), 0.00001);		
		
	}
	
	
	@Test
	//@Ignore
	public void naoDeveDevolverUmFilmeNoDomingo() {
		
		Assume.assumeTrue( LocalDate.now().getDayOfWeek() ==  DayOfWeek.SATURDAY );
		
		// cenário
		Filme filme = new Filme("Batman o Retorno", 2, 5.0);

		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		// verificação
		LocalDate dataRetorno = locacao.getDataRetorno();
		
		System.out.println(dataRetorno.getDayOfWeek() );

		
		Assert.assertTrue(dataRetorno.getDayOfWeek() == DayOfWeek.MONDAY );
				
	}

}