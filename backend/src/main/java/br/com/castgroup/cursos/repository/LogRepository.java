package br.com.castgroup.cursos.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.castgroup.cursos.dtos.LogDTO;
import br.com.castgroup.cursos.entities.Log;

public interface LogRepository extends JpaRepository<Log, Integer>{
	   
	   
	@Query("SELECT new br.com.castgroup.cursos.dtos.LogDTO(l.inclusao, l.ultimaAtualizacao, u.nome, c.descricao, l.id_log, l.acao) "
			+ "from Log l join Usuario u on u.idUsuario = l.usuario.idUsuario"
			+ " join Curso c on c.id_curso = l.curso.id_curso")	
	Page<LogDTO> listarLogs(Pageable paginacao);
}
