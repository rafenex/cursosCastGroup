package br.com.castgroup.cursos.form;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.repository.CategoriaRepository;
import br.com.castgroup.cursos.repository.CursoRepository;

public class FormCadastroCurso {
	private String descricao;
	private LocalDate inicio;
	private LocalDate termino;
	private Integer quantidade;
	private Integer idCategoria;
	
	public FormCadastroCurso() {
		// TODO Auto-generated constructor stub
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getTermino() {
		return termino;
	}

	public void setTermino(LocalDate termino) {
		this.termino = termino;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}




	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Curso converter(CategoriaRepository categoriaRepository, CursoRepository cursoRepository, String body) {
		Curso curso = new Curso();

		curso.setCategoria(categoriaRepository.getById(idCategoria));
		curso.setDescricao(descricao);
		curso.setInicio(inicio);
		curso.setTermino(termino);
		return curso;
	}
	
	

	
	
}
