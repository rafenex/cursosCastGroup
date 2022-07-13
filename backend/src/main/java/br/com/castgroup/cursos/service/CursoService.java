package br.com.castgroup.cursos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.entities.Log;
import br.com.castgroup.cursos.entities.Usuario;
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
	
	@PersistenceContext
	EntityManager em;

	Usuario usuarioLogado = new Usuario();

	public void usuarioLogado(Usuario usuario) {
		usuarioLogado = usuario;
	}
		

	@Transactional
	public void cadastrarCurso(Curso curso) {
		validaData(curso);
		existeCategoria(curso);
		existeDescricao(curso);
		finalizado(curso);
		@SuppressWarnings("deprecation")
		Log log = new Log(null, LocalDate.now(), LocalDate.now(), curso, "Cadastrou Curso",
				usuarioRepository.getOne(usuarioLogado.getIdUsuario()));
		curso.setInclusao(LocalDate.now());
		logRepository.save(log);
		cursoRepository.save(curso);
	}

	@Transactional
	public void atualizarCurso(Curso request, Integer id_curso) {
		Optional<Curso> item = cursoRepository.findById(id_curso);
		if (item.isEmpty()) {
			throw new RuntimeException("Curso não encontrado");
		} else {
			Curso curso = item.get();
			System.out.println(curso.getInclusao());
			validaDataUpdate(request);
			existeDescricaoUpdate(request);
			request.setInclusao(curso.getInclusao());
			BeanUtils.copyProperties(request, curso);

			finalizado(curso);

			existeCategoria(curso);

			@SuppressWarnings("deprecation")
			Log log = new Log(null, item.get().getInclusao(), LocalDate.now(), curso, "Atualizou Curso",
					usuarioRepository.getOne(usuarioLogado.getIdUsuario()));
			logRepository.save(log);
			cursoRepository.save(curso);
		}
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
			@SuppressWarnings("deprecation")
			Log log = new Log(null, curso.getInclusao(), LocalDate.now(), curso, "Deletou Curso",
					usuarioRepository.getOne(usuarioLogado.getIdUsuario()));
			logRepository.save(log);
			cursoRepository.delete(curso);
		}
	}
	
	// GET
	public List<Curso> acharTodos() {
		checarTerminoCurso();
		return cursoRepository.findAll();
	}
	
	public List<Curso> listarPorData(LocalDate inicio, LocalDate termino) {
		return cursoRepository.cursosPorData(inicio, termino);
	}

	public Optional<Curso> acharPorId(Integer id_curso) {
		Optional<Curso> item = cursoRepository.findById(id_curso);
		if (item.isEmpty()) {
			throw new RuntimeException("Curso não encontrado");
		}
		return item;
	}

	public List<Curso> acharPorDescricao(String descricao) {
		List<Curso> listaDeCursos = cursoRepository.findByDescricao(descricao);
		if (listaDeCursos.isEmpty()) {
			throw new RuntimeException("Não foi encotrado nenhum curso com esse nome");
		}
		return listaDeCursos;
	}

//	public List<Curso> filtrar(String descricao, LocalDate inicio, LocalDate termino) {
//		if((inicio != null) && (termino != null) && (descricao!= null)) {
//			checarTerminoCurso();
//			return cursoRepository.cursosPorDataEDescricao(descricao, inicio, termino);		
//		} 
//		if((inicio != null) && (termino != null) && (descricao == null)) {
//			checarTerminoCurso();
//			return cursoRepository.cursosPorData(inicio, termino);
//		} 		
//		if((inicio == null) && (termino == null) && (descricao != null)) {
//			checarTerminoCurso();
//			return cursoRepository.findByDescricao(descricao);
//		} 		
//		checarTerminoCurso();
//			return cursoRepository.findAll();
//		}
		
	public List<Curso> filtrar(String descricao, LocalDate inicio, LocalDate termino){
		CriteriaBuilder cb = em.getCriteriaBuilder(); //constroi CB
		CriteriaQuery<Curso> cq = cb.createQuery(Curso.class); //QUERY CURSO
		Root<Curso> curso = cq.from(Curso.class); // SELECT * FROM CURSO
		List<Predicate>predicates = new ArrayList<>(); //pode ser vazia ou nao
		if (descricao != null) {
			Predicate descricaoPredicate = cb.equal(curso.get("descricao"), descricao);
			predicates.add(descricaoPredicate);
		}
		if (inicio !=null) {
			Predicate inicioPredicate = cb.greaterThanOrEqualTo(curso.get("inicio"), inicio);
			predicates.add(inicioPredicate);
		}
		if (termino !=null) {
			Predicate descricaoPredicate = cb.lessThanOrEqualTo(curso.get("termino"), termino);
			predicates.add(descricaoPredicate);
		}
		Predicate[]predicateArr = new Predicate[predicates.size()];
		predicates.toArray(predicateArr);
		cq.where(predicateArr);
		TypedQuery<Curso>query = em.createQuery(cq);
		return query.getResultList();
		
	}
		

	
	//Validações		

	private void validaData(Curso request) {

		if (request.getInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data de inicio menor que a data atual");
		}

		if (request.getInicio().isAfter(request.getTermino())) {
			throw new RuntimeException("Data de início após data de término");
		}
		if (cursoRepository.contador(request.getInicio(), request.getTermino()) > 0) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
	}

	private void validaDataUpdate(Curso request) {
		List<Curso> cursosPorData = cursoRepository.cursosPorData(request.getInicio(), request.getTermino());
		if (cursoRepository.contador(request.getInicio(), request.getTermino()) == 1) {
			Boolean valido = false;
			for (Curso curso : cursosPorData) {
				if (curso.getId_curso().equals(request.getId_curso())) {
					valido = true;
				}
			}
			if (!valido)
				throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
		if (request.getInicio().isAfter(request.getTermino())) {
			throw new RuntimeException("Data de início após data de término");
		}
		if (cursoRepository.contador(request.getInicio(), request.getTermino()) > 1) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
	}
	
	private void existeCategoria(Curso request) {
		Optional<Categoria> categoria = categoriaRepository.findById(request.getCategoria().getId_categoria());
		if (categoria.isEmpty()) {
			throw new RuntimeException("Não existe a categoria informada");
		}
	}

	private void existeDescricaoUpdate(Curso request) {
		List<Curso> findByDescricao = cursoRepository.findByDescricao(request.getDescricao());
		if (findByDescricao.size() == 2) {
			Boolean valido = false;
			for (Curso curso : findByDescricao) {
				if (curso.getId_curso().equals(request.getId_curso())) {
					valido = true;
				}
			}
			if (!valido)
				throw new RuntimeException("Curso já cadastrado.");
		}
		
		
		
		
		

		if (findByDescricao.size() == 1) {
			Boolean valido = false;
			for (Curso curso : findByDescricao) {
				if (curso.getId_curso().equals(request.getId_curso())) {
					valido = true;
				}
			}
			if (!valido)
				throw new RuntimeException("Curso já cadastrado.");
		}
	}

	private void existeDescricao(Curso curso) {
		List<Curso> findByDescricao = cursoRepository.findByDescricao(curso.getDescricao());
		List<Curso> naoFinalizado = cursoRepository.findByDescricaoAndFinalizado(curso.getDescricao(),false);
		System.out.println(naoFinalizado.size());
		System.out.println(findByDescricao.size());
		if ((findByDescricao.size() > 0) && (naoFinalizado.size()>0)) {
			throw new RuntimeException("Curso já cadastrado.");
		}
	}
	
	public void checarTerminoCurso() {
		List<Curso> lista = cursoRepository.findAll();
		for (Curso curso : lista) {
			if (curso.getTermino().isBefore(LocalDate.now())) {
				curso.setFinalizado(true);
				cursoRepository.save(curso);
			} else {
				curso.setFinalizado(false);
			}	
		}
	}

	public void finalizado(Curso curso) {
		if (curso.getTermino().isBefore(LocalDate.now())) {
			curso.setFinalizado(true);
		} else {
			curso.setFinalizado(false);
		}
	}


}
