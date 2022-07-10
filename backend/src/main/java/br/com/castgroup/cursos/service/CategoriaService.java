package br.com.castgroup.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.repository.CategoriaRepository;
@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> acharTodos() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

}
