package dcomp.es2.locadora.repositorio;

import dcomp.es2.locadora.modelo.Usuario;

public interface UsuarioRepository {

	Usuario porNome(String nome);

}