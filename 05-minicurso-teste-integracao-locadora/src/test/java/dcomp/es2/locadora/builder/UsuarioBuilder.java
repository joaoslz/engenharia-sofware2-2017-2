package dcomp.es2.locadora.builder;

import dcomp.es2.locadora.modelo.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	
	private static int contadorID = 1;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario() {
		
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setNome("Usuario 1");
		builder.usuario.setId(contadorID++ );
		return builder;
	}
	
	public UsuarioBuilder comNome(String nome) {
		usuario.setNome(nome);
		return this;
	}
	
	
	public Usuario constroi(){
		return usuario;
	}
}