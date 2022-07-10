package br.com.castgroup.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.castgroup.cursos.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("select u from Usuario u where u.login =:login")
	Usuario findByLogin(@Param("login") String login);

	
	//obter um usuario ded dados atraves do login e senha
	@Query("select u from Usuario u where u.login = :login and u.senha = :senha")	
	Usuario findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
}