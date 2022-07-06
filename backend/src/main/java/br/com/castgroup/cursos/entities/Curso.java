package br.com.castgroup.cursos.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_curso")
public class Curso {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_curso;


	private String descricao;
	
	@NotNull
	private LocalDate inicio;
	@NotNull
	private LocalDate termino;
	private Integer quantidadeAlunos;
	
	


	@ManyToOne
	private Categoria categoria;
	
	public Curso() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getId_curso() {
		return id_curso;
	}



	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
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



	public Integer getQuantidadeAlunos() {
		return quantidadeAlunos;
	}

	public void setQuantidadeAlunos(Integer quantidadeAlunos) {
		this.quantidadeAlunos = quantidadeAlunos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	

}