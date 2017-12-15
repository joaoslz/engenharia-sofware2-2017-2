package dcomp.es2.locadora.repositorio;

import javax.persistence.EntityManager;

import dcomp.es2.locadora.modelo.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepository {

	
	
	private EntityManager manager;

	public UsuarioRepositoryImpl(EntityManager manager ) {
		this.manager = manager;
	}
	
	
	@Override
	public Usuario porNome(String nome) {
		
		return null;
	}

}
