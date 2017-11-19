package dcomp.es2.locadora.servico;

import java.time.LocalDate;
import java.util.Date;

import dcomp.es2.locadora.modelo.Filme;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.modelo.Usuario;
import dcomp.es2.locadora.utils.DataUtils;


public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		if (filme.getEstoque() == 0) {
			throw new RuntimeException("Sem Estoque");
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(LocalDate.now() );
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
    	LocalDate amanha = LocalDate.now().plusDays(1);
		locacao.setDataRetorno(amanha );
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}