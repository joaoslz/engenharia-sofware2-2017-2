package dcomp.es2.locadora.servico;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dcomp.es2.locadora.builder.FilmeBuilder;
import dcomp.es2.locadora.builder.LocacaoBuilder;
import dcomp.es2.locadora.builder.UsuarioBuilder;
import dcomp.es2.locadora.modelo.Filme;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.modelo.Usuario;
import dcomp.es2.locadora.repositorio.LocacaoRepository;
import dcomp.es2.locadora.utils.DataUtils;

public class LocacaoServiceTestV2 {
	
	
	private LocacaoServiceV2 locacaoService;
	private Usuario usuario;
	private LocacaoRepository locacaoRepository;
	private SPCService spcService;
	private EmailService emailService;

	
	@Before
	public void setup() {
		usuario = UsuarioBuilder.umUsuario().constroi();

		locacaoService = new LocacaoServiceV2();
		locacaoRepository = Mockito.mock(LocacaoRepository.class);
		spcService = Mockito.mock(SPCService.class );
		emailService = Mockito.mock(EmailService.class );
		
		locacaoService.setLocacaoRepository(locacaoRepository);
		locacaoService.setSpcService(spcService);
		locacaoService.setEmailService(emailService );
	}

	@Test
	public void testaUmaLocacao() {
		
		Assume.assumeFalse( LocalDate.now().getDayOfWeek() ==  DayOfWeek.SATURDAY );

		// cenário
		Filme filme = FilmeBuilder.umFilme().constroi();
				
		// ação
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);

		// verificação
		Assert.assertThat(locacao.getValor(), is(equalTo(4.0)));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), LocalDate.now() ), is(true) );
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.amanha()), is(true) );
		
		
		Mockito.verify(locacaoRepository, Mockito.times(1)).salva(locacao);
	}

	
	@Test(expected=RuntimeException.class)
	public void naoDeveAlugarFilmeSemEstoque() {
		
		Filme filme = FilmeBuilder
		                .umFilme()
		                .semEstoque()
		                .constroi();
		
		locacaoService.alugarFilme(usuario, filme);

		
	}

	
	@Test
	public void deveAplicarDesconto10PctNoSegundoFilme() {
		
		// cenário
		List<Filme> filmes = Arrays.asList( FilmeBuilder.umFilme().constroi(),
				                            FilmeBuilder.umFilme().constroi());                
		
		//ação
		Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);
		
		// verificação
		// 4 + 4*90% = 4 + 3.60 = 7.60
		Assert.assertThat(locacao.getValor(), is(7.60d) );		
	}
	
	@Test
	public void deveAplicar30PctDeDescontoNoTerceiroFilme() {
		
		// cenário
		List<Filme> filmes = Arrays.asList( FilmeBuilder.umFilme().constroi(),
				                            FilmeBuilder.umFilme().constroi(),
				                            FilmeBuilder.umFilme().constroi() );                
		//ação
		Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);
		
		// verificação
		// 4 + 4*90% + 4 * 0.70 = 4 + 3.60 + 2.80 = 10.40d
		Assert.assertEquals(10.40d, locacao.getValor(), 0.00001);		
		
	}
	
	@Test
	public void deveAplicar50PctDeDescontoNoQuartoFilme() {
		
		// cenário
		List<Filme> filmes = Arrays.asList( FilmeBuilder.umFilme().constroi(),
                                            FilmeBuilder.umFilme().constroi(),
                                            FilmeBuilder.umFilme().constroi(),                
											FilmeBuilder.umFilme().constroi() );                
		//ação
		Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);
		
		// verificação
		// 4 + 4*90% + 4 * 0.70 = 4 + 3.60 + 2.80 + 2.0 = 12.40
		Assert.assertEquals(12.40d, locacao.getValor(), 0.00001);		
		
	}
	
	
	@Test
	//@Ignore
	public void naoDeveDevolverUmFilmeNoDomingo() {
		
		Assume.assumeTrue( LocalDate.now().getDayOfWeek() ==  DayOfWeek.SATURDAY );
		
		// cenário
		Filme filme = new Filme("Batman o Retorno", 2, 5.0);

		// ação
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		
		// verificação
		LocalDate dataRetorno = locacao.getDataRetorno();
		
		System.out.println(dataRetorno.getDayOfWeek() );

		Assert.assertTrue(dataRetorno.getDayOfWeek() == DayOfWeek.MONDAY );
				
	}
	
	@Test(expected=IllegalStateException.class)
	public void naoDeveAlugarFilmeParaNegativadoNoSPC() {
		
		Filme filme = FilmeBuilder.umFilme().constroi();
		
		//instrumentacao do mock
		Mockito.when(spcService.possuiNegativacao(usuario)).thenReturn(true );
		
		// fazer um teste com usuario 2;
		
		locacaoService.alugarFilme(usuario, filme);
		
		
	}
	
	@Test
	public void deveEnviarEmailParaLocacoesAtrasadas() {
		
		Usuario usuario2 = UsuarioBuilder.umUsuario().comNome("Usuario 2").constroi();
		Usuario usuario3 = UsuarioBuilder.umUsuario().comNome("Usuario 3").constroi();
		Usuario usuario4 = UsuarioBuilder.umUsuario().comNome("Usuario 4").constroi();
		
		List<Locacao> locacoes = Arrays.asList(LocacaoBuilder.umaLocacao().emAtraso().paraUsuario(usuario).constroi(),
				                               LocacaoBuilder.umaLocacao().emAtraso().paraUsuario(usuario2).constroi(),
				                               LocacaoBuilder.umaLocacao().emAtraso().paraUsuario(usuario3).constroi());
		
		Mockito.when(locacaoRepository.getLocacoesPendentes() ).thenReturn(locacoes );
		
		locacaoService.notificarAtrasos();
		
		Mockito.verify(emailService, times(1) ).notificarAtrasoDo(usuario);
		Mockito.verify(emailService).notificarAtrasoDo(usuario2);
		Mockito.verify(emailService).notificarAtrasoDo(usuario3);
		Mockito.verify(emailService).notificarAtrasoDo(usuario3);

		Mockito.verify(emailService, Mockito.never() ).notificarAtrasoDo(usuario4);
		
		Mockito.verifyNoMoreInteractions(emailService );
		
		
	}
	

}