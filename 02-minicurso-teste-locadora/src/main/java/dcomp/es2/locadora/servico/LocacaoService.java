package dcomp.es2.locadora.servico;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import dcomp.es2.locadora.modelo.Filme;
import dcomp.es2.locadora.modelo.Locacao;
import dcomp.es2.locadora.modelo.Usuario;


public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		
		if (filme.getEstoque() == 0) {
			throw new RuntimeException("Sem Estoque");
		}
		
		return this.alugarFilmes(usuario, Arrays.asList(filme ));
	}
	
	public Locacao alugarFilmes(Usuario usuario, List<Filme> filmes) {
		
	   Locacao locacao = new Locacao();
	   locacao.setFilmes(filmes );
	   locacao.setUsuario(usuario);
	   locacao.setDataLocacao(LocalDate.now() );
	   
	   
	   LocalDate dataRetorno = LocalDate.now().plusDays(1);
	   
	   if (dataRetorno.getDayOfWeek() == DayOfWeek.SUNDAY ) {
		   dataRetorno = dataRetorno.plusDays(1);
	   }
	   
	   locacao.setDataRetorno(dataRetorno );
	   
	   double valorTotal = calculaValorDaLocacao(filmes);

       locacao.setValor(valorTotal);
		
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
       //daoLocacao.salva(locacao);
		
		return locacao;
	}

	
	private double calculaValorDaLocacao(List<Filme> filmes) {
		double valorTotal = 0d;
		   
		   for(int i=1; i <= filmes.size(); i++) {
			   double valorFilme = filmes.get(i-1).getPrecoLocacao();
			   
			   
			   switch(i) {
			   
			   case 2: valorFilme = valorFilme * 0.90; break;
			   case 3: valorFilme = valorFilme * 0.70; break;
			   case 4: valorFilme = valorFilme * 0.50; break;

			   
			   }
		 	   
			   valorTotal += valorFilme;
		   }
		return valorTotal;
	}
	
	
	
	
}