package br.com.castgroup.cursos.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.service.CursoService;
import io.swagger.annotations.ApiOperation;


@CrossOrigin
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

	@Autowired
	CursoService cursoService;
	
	@ApiOperation("Serviço para cadastrar cursos")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>cadastrar(@RequestBody Curso request){
		try {
			cursoService.cadastrarCurso(request);
			return ResponseEntity.status(HttpStatus.CREATED).body("Curso cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}	
	
	
	@ApiOperation("Serviço para atualizar cursos")
	@PutMapping(value = "/{id_curso}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>atualizar(@PathVariable("id_curso") Integer id_curso, @RequestBody Curso request){
		try {
			cursoService.atualizarCurso(request, id_curso);
			return ResponseEntity.status(HttpStatus.CREATED).body("Curso atualizado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para listar cursos por data")
	@GetMapping(value = "/data/{inicio}/{termino}")
	public List<Curso> findByData(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate termino) {		
		return cursoService.listarPorData(inicio, termino);
	}
	
	@ApiOperation("Serviço para achar curso por id")
	@GetMapping(value = "/{id_curso}")
	public ResponseEntity<?> findById(@PathVariable("id_curso") Integer id_curso) {		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cursoService.acharPorId(id_curso));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}		
	}


	@ApiOperation("Serviço para listar cursos")
	@GetMapping
	public List<Curso> listarCursos() {		
		return cursoService.acharTodos();
	}

	@ApiOperation("Serviço para listar cursos por descrição")
	@GetMapping(value = "/nome/{descricao}")
	public ResponseEntity<?> findByDescricao(@PathVariable("descricao") String descricao) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cursoService.acharPorDescricao(descricao));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	
	@ApiOperation("Serviço para deletar cursos")
	@DeleteMapping(value = "/{id_curso}")
	public ResponseEntity<String> deleteById(@PathVariable("id_curso") Integer id_curso) {		
		try {
			cursoService.deletarCursoPorId(id_curso);		
			return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}	
	}
	

		
	@ApiOperation("Serviço para filtrar cursos")
	@GetMapping(value="/filtro")
	public Page<Curso> listar(
							   @RequestParam(required = false) String descricao,
							   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
							   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate termino,
							   @PageableDefault(direction = Direction.ASC, page = 0, size = 10)
							   Pageable paginacao){
			return cursoService.filtrar(descricao, inicio, termino, paginacao);	
	}



}
