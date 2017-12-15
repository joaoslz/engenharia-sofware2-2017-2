package dcomp.es2.locadora.builder;

import java.time.LocalDate;
import java.util.Arrays;

import dcomp.es2.locadora.modelo.Filme;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.modelo.Usuario;

public class LocacaoBuilder {

	private Locacao locacao;
	
	
	private LocacaoBuilder(){}

	public static LocacaoBuilder umaLocacao() {
		LocacaoBuilder builder = new LocacaoBuilder();
		
		builder.locacao = new Locacao();
		
		builder.locacao.setUsuario(UsuarioBuilder.umUsuario().constroi() );
		
		builder.locacao.setFilmes(Arrays.asList(FilmeBuilder.umFilme().constroi() ) );
		
		builder.locacao.setDataLocacao(LocalDate.now() );
		builder.locacao.setDataRetorno(LocalDate.now().plusDays(1) );
		
		builder.locacao.setValor(4.0);

		return builder;
	}

	
	public LocacaoBuilder paraUsuario(Usuario usuario) {
		locacao.setUsuario(usuario);
		return this;
	}

	public LocacaoBuilder comListaFilmes(Filme... filme) {
		locacao.setFilmes(Arrays.asList(filme) );
		return this;
	}

	public LocacaoBuilder comDataLocacao(LocalDate dataLocacao) {
		locacao.setDataLocacao(dataLocacao);
		return this;
	}

	public LocacaoBuilder comDataRetorno(LocalDate dataRetorno) {
		locacao.setDataRetorno(dataRetorno);
		return this;
	}
	
	public LocacaoBuilder emAtraso() {
		locacao.setDataLocacao(LocalDate.now().minusDays(5) );
		locacao.setDataRetorno(LocalDate.now().minusDays(6) );
		return this;
	}
	

	public LocacaoBuilder comValor(Double valor) {
		locacao.setValor(valor);
		return this;
	}

	public Locacao constroi() {
		return locacao;
	}
	

}
