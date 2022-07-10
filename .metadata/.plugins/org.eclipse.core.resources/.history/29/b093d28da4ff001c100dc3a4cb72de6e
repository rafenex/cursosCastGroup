package br.com.castgroup.cursos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.entities.Log;
import br.com.castgroup.cursos.repository.CategoriaRepository;
import br.com.castgroup.cursos.repository.CursoRepository;
import br.com.castgroup.cursos.repository.LogRepository;
import br.com.castgroup.cursos.repository.UsuarioRepository;

@Service
public class CursoService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	LogRepository logRepository;

	@Autowired
	CursoRepository cursoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	private String message = null;

	public String mensagem() {
		return message;
	}

	@Transactional
	public void cadastrarCurso(Curso curso) {
		validaData(curso);
		existeCategoria(curso, categoriaRepository);
		existeDescricao(curso, cursoRepository);
		Log log = new Log(null, LocalDate.now(), LocalDate.now(), curso, usuarioRepository.getById(1));
		curso.setInclusao(LocalDate.now());
		logRepository.save(log);
		cursoRepository.save(curso);
	}


	private void existeDescricao(Curso curso, CursoRepository cursoRepository) {
		List<Curso> findByDescricao = cursoRepository.findByDescricao(curso.getDescricao());
		if(findByDescricao.size()>0) {
			throw new RuntimeException("Curso já cadastrado");
		}
		
	}

	private void existeCategoria(Curso request, CategoriaRepository categoriaRepository) {
		Optional<Categoria> categoria = categoriaRepository.findById(request.getCategoria().getId_categoria());
		if (categoria.isEmpty()) {
			throw new RuntimeException("Não existe a categoria informada");
		}
	}

	private void validaData(Curso request) {
		if (request.getInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data de inicio anterior a data atual");
		}
		if (request.getInicio().isAfter(request.getTermino())) {
			throw new RuntimeException("Inicio depois do fim");
		}
		if (cursoRepository.contador(request.getInicio(), request.getTermino()) > 0) {			
			throw new RuntimeException("Já existe curso nessa data");
		}
	}

	private void validaDataUpdate(Curso request) {
		if (request.getInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data de inicio anterior a data atual");
		}
		if (request.getInicio().isAfter(request.getTermino())) {
			throw new RuntimeException("Inicio depois do fim");
		}
		System.out.println(request.getDescricao());
		System.out.println(cursoRepository.contador(request.getInicio(), request.getTermino()));
		if (cursoRepository.contador(request.getInicio(), request.getTermino()) > 1) {
			
			throw new RuntimeException("Já existe curso nessa data");
		}
	}

	@Transactional
	public void atualizarCurso(Curso request, Integer id_curso) {
		Optional<Curso> item = cursoRepository.findById(id_curso);
		if (item.isEmpty()) {
			throw new RuntimeException("Curso não encontrado");
		} else {			
		Curso curso = item.get();
		converter(request, curso);
		validaDataUpdate(curso);
		existeCategoria(curso, categoriaRepository);
		Log log = new Log(null, curso.getInclusao(), LocalDate.now(), curso, usuarioRepository.getById(1));
		logRepository.save(log);
		cursoRepository.save(curso);
	}
	}
	
	public void converter(Curso request, Curso curso) {
		curso.setCategoria(request.getCategoria());
		curso.setDescricao(request.getDescricao());
		curso.setFinalizado(request.getFinalizado());
		curso.setInclusao(request.getInicio());
		curso.setInicio(request.getInicio());
		curso.setTermino(request.getTermino());				
	}



	public void finalizado(Curso curso) {
		if (curso.getTermino().isBefore(LocalDate.now())) {
			curso.setFinalizado(true);
		} else {
			curso.setFinalizado(false);
		}
	}

	public List<Curso> listarPorData(LocalDate inicio, LocalDate termino) {
		return cursoRepository.findByInicioBetween(inicio, termino);
	}

	public Optional<Curso> acharPorId(Integer id_curso) {
		Optional<Curso> item = cursoRepository.findById(id_curso);
		if (item.isEmpty()) {
			throw new RuntimeException("Curso não encontrado");
		}
		return item;		
	}

	public List<Curso> acharTodos() {
		return cursoRepository.findAll();
		
	}

	public List<Curso> acharPorDescricao(String descricao) {
		List<Curso> listaDeCursos = cursoRepository.findByDescricao(descricao);
		if (listaDeCursos.isEmpty()) {
			throw new RuntimeException("Não foi encotrado nenhum curso");
		}
		return listaDeCursos;
		
	}

	@Transactional
	public void deletarCursoPorId(Integer id_curso) {
			Optional<Curso> item = cursoRepository.findById(id_curso);
			if (item.isEmpty()) {
				throw new RuntimeException("Curso não encontrado");
			} else {
				Curso curso = item.get();
				if (curso.getFinalizado()) {					
					throw new RuntimeException("Curso finalizado não pode ser excluido");
				}
				Log log = new Log(null,curso.getInclusao(),LocalDate.now(), curso, usuarioRepository.getById(1));
				logRepository.save(log);
				cursoRepository.delete(curso);
			}		
	}
}
