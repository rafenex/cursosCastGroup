package br.com.castgroup.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.cursos.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
