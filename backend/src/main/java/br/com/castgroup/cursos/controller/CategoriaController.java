package br.com.castgroup.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
@RestController
@CrossOrigin
@RequestMapping("/api/categorias")
public class CategoriaController {
	

	@Autowired
	CategoriaService categoriaService;
	
	@ApiOperation("Serviço para listar categorias")
	@GetMapping
	public List<Categoria> listarCursos() {		
		return categoriaService.acharTodos();
	}
	
	@ApiOperation("Serviço para cadastrar categorias")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>cadastrar(@RequestBody Categoria categoria){
		try {
			categoriaService.cadastrar(categoria);
			return ResponseEntity.status(HttpStatus.CREATED).body("Categoria cadastrada com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	

}
