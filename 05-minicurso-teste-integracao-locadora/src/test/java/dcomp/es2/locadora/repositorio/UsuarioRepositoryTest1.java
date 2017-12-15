package dcomp.es2.locadora.repositorio;

import static org.hamcrest.CoreMatchers.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import dcomp.es2.locadora.modelo.Usuario;

public class UsuarioRepositoryTest1 {

    @Test
    public void deveEncontrarUsuarioPeloNomeComMock() {
    	
        EntityManager manager = Mockito.mock(EntityManager.class);
        
        TypedQuery<Usuario> query = Mockito.mock(TypedQuery.class);
        
        UsuarioRepository usuarios = new UsuarioRepositoryImpl(manager);

        Usuario usuario = new Usuario("João da Silva");
        
        String sql = "SELECT u FROM Usuario u WHERE u.nome = :pNome";

        Mockito.when(manager.createQuery(sql, Usuario.class)).thenReturn(query);
        Mockito.when(query.setParameter("pNome", "João da Silva")).thenReturn(query);
        
        Mockito.when(query.getSingleResult()).thenReturn(usuario);

        Usuario usuarioDoBanco = usuarios.buscaPorNome("João da Silva" );

        Assert.assertThat(usuario.getNome(), is(equalTo(usuarioDoBanco.getNome()) ));
    }
    
    
/*    @Test
    public void deveEncontrarUsuarioPeloNome() {
    	
        EntityManager manager = Persistence.createEntityManagerFactory("locadoraPU")
        		                           .createEntityManager();
        
        UsuarioRepository usuarios = new UsuarioRepositoryImpl(manager);
        
        Usuario usuarioDoBanco = usuarios.buscaPorNome("João da Silva" );

        Assert.assertThat("João da Silva", is(equalTo(usuarioDoBanco.getNome()) ));
    }
	*/	

    
    @Test
    public void deveEncontrarUsuarioPeloNome() {
    	
        EntityManager manager = Persistence.createEntityManagerFactory("locadoraPU_test")
        		                           .createEntityManager();
        
        manager.getTransaction().begin();
        UsuarioRepository usuarios = new UsuarioRepositoryImpl(manager);
        
        usuarios.salva( new Usuario("João da Silva") );
        manager.flush();
        manager.clear();
        
        Usuario usuarioDoBanco = usuarios.buscaPorNome("João da Silva" );

        manager.getTransaction().commit();
        
        Assert.assertThat("João da Silva", is(equalTo(usuarioDoBanco.getNome()) ));
        
    }
    
    
    @Test(expected=NoResultException.class)
    public void naoDeveEncontrarUsuarioPeloNome() {
    	
        EntityManager manager = Persistence.createEntityManagerFactory("locadoraPU")
        		                           .createEntityManager();
        
        UsuarioRepository usuarios = new UsuarioRepositoryImpl(manager);
        
        Usuario usuarioDoBanco = usuarios.buscaPorNome("Pedro Jose" );
        
    }
	
}
