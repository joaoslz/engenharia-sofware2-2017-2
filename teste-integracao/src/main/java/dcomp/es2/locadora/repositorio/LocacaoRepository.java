package dcomp.es2.locadora.repositorio;

import java.util.List;

import dcomp.es2.locadora.modelo.Locacao;

public interface LocacaoRepository {
	
	void salva(Locacao locacao);

	List<Locacao> buscaLocacoesEmAtraso();
}
