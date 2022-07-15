package br.com.castgroup.cursos.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.castgroup.cursos.dtos.LogDTO;
import br.com.castgroup.cursos.entities.Log;


public interface LogRepository extends JpaRepository<Log, Integer>{   
	   
	@Query("SELECT new br.com.castgroup.cursos.dtos.LogDTO(l.inclusao, l.ultimaAtualizacao, u.nome, l.id_log, l.curso.id_curso, l.acao) "
			+ "from Log l join Usuario u on u.idUsuario = l.usuario.idUsuario")	
	Page<LogDTO> listarLogs(Pageable paginacao);
	
	@Query("SELECT new br.com.castgroup.cursos.dtos.LogDTO(l.inclusao, l.ultimaAtualizacao, u.nome,l.id_log,l.curso.id_curso, l.acao) "
	+ "from Log l join Usuario u on u.idUsuario = l.usuario.idUsuario"
	+ " WHERE l.curso.id_curso = :id_curso")	
	Page<LogDTO> listarPorId(Pageable paginacao, @Param("id_curso") Integer id_curso);	

}
	

