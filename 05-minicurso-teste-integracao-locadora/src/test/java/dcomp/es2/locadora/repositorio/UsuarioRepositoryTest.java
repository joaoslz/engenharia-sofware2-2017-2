package dcomp.es2.locadora.repositorio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dcomp.es2.locadora.modelo.Usuario;

public class UsuarioRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private UsuarioRepository usuarios;

	@BeforeClass
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@Before
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		usuarios = new UsuarioRepositoryImpl(manager);
	}

	@After
	public void depois() {
		manager.getTransaction().rollback();
	}

	@AfterClass
	public static void fim() {
		emf.close();
	}

	@Test
	public void deveEncontrarUsuarioPeloNome() {

		usuarios.salva(new Usuario("João da Silva"));
		manager.flush();
		manager.clear();

		Usuario usuarioDoBanco = usuarios.buscaPorNome("João da Silva");

		Assert.assertThat("João da Silva", is(equalTo(usuarioDoBanco.getNome())));
	}

	@Test(expected = NoResultException.class)
	public void naoDeveEncontrarUsuarioPeloNome() {
		Usuario usuarioDoBanco = usuarios.buscaPorNome("Pedro Jose");
	}

	@Test(expected = NoResultException.class)
	public void deveExcluirUmUsuario() {
		Usuario usuario = new Usuario("João Carlos");

		usuarios.salva(usuario);
		usuarios.exclui(usuario);
		manager.flush();
		manager.clear();

		Usuario usuarioExcluido = usuarios.buscaPorNome("João Carlos");

	}

	@Test(expected = NoResultException.class)
	public void deveAlterarUmUsuario() {
		Usuario usuario = new Usuario("João Carlos");

		usuarios.salva(usuario);

		usuario.setNome("João da Silva");

		usuarios.atualiza(usuario);

		manager.flush();

		Usuario novoUsuario = usuarios.buscaPorNome("João da Silva" );
		Assert.assertNotNull(novoUsuario);
		
		System.out.println(novoUsuario);

		Usuario usuarioInexistente = usuarios.buscaPorNome("João Carlos");
	}

}
