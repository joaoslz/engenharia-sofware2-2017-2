package dcomp.es2.locadora.repositorio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Locacao;

public class LocacaoRepositoryImpl implements LocacaoRepository {

	
	private EntityManager manager;

	public LocacaoRepositoryImpl(EntityManager manager ) {
		this.manager = manager;
	}
	
	@Override
	public void salva(Locacao locacao) {
		manager.merge(locacao);

	}
	
	@Override
	public List<Locacao> buscaLocacoesEmAtraso() {
		return manager
				.createQuery("select c from Locacao c where c.dataDevolucao is null"
				                    + "and :dataCorrente > c.dataRetorno", Locacao.class)
				.setParameter("dataCorente", LocalDate.now() )
				.getResultList();
	} 
}
