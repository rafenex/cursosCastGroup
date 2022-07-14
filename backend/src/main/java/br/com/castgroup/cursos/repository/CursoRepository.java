package br.com.castgroup.cursos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.castgroup.cursos.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
	List<Curso> findByDescricao(String descricao);	

	@Query("select count(*) from Curso c where (:inicio <= c.inicio and :termino >= c.inicio)"
			+ "OR"
			+ "								   (:inicio <= c.termino and :termino >= c.termino)"
			+ "OR"
			+ "								   (:inicio >= c.inicio and :termino <= c.termino)")	
	Integer contador(LocalDate inicio, LocalDate termino);
	
	@Query("select c from Curso c where (:inicio <= c.inicio and :termino >= c.inicio)"
			+ "OR"
			+ "								   (:inicio <= c.termino and :termino >= c.termino)"
			+ "OR"
			+ "								   (:inicio >= c.inicio and :termino <= c.termino)")	
	List<Curso> cursosPorData(LocalDate inicio, LocalDate termino);
	
	
	@Query("select c from Curso c where ((:inicio <= c.inicio and :termino >= c.inicio)"
			+ "OR"
			+ "								   (:inicio <= c.termino and :termino >= c.termino)"
			+ "OR"
			+ "								   (:inicio >= c.inicio and :termino <= c.termino))"
			+ "AND                             						 (:descricao = c.descricao)")	
	List<Curso> cursosPorDataEDescricao(String descricao, LocalDate inicio, LocalDate termino);
	
	
	List<Curso> findByDescricaoAndFinalizado(String descricao, Boolean finalizado);
	




}
