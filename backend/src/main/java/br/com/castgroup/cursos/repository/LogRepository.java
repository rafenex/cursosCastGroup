package br.com.castgroup.cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.castgroup.cursos.dtos.LogDTO;
import br.com.castgroup.cursos.entities.Log;

public interface LogRepository extends JpaRepository<Log, Integer>{
	   
	@Query("SELECT new br.com.castgroup.cursos.dtos.LogDTO(u.nome, c.descricao, l.id_log, c.id_curso, l.acao, l.usuario.idUsuario) "
			+ "from Log l join Usuario u on u.idUsuario = l.usuario.idUsuario"
			+ " join Curso c on c.id_curso = l.curso.id_curso")	
	List<LogDTO> listarLogs();

}
