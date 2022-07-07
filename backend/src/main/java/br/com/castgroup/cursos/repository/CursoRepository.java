package br.com.castgroup.cursos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.castgroup.cursos.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
	List<Curso> findByDescricao(String descricao);
	
	List<Curso> findByInicioBetween(LocalDate dataInicioStart, LocalDate dataInicoEnd);
	

}
