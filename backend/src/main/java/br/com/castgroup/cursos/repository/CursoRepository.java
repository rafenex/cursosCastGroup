package br.com.castgroup.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.cursos.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

}