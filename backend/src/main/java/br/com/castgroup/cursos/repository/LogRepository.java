package br.com.castgroup.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.cursos.entities.Log;

public interface LogRepository extends JpaRepository<Log, Integer>{

}
