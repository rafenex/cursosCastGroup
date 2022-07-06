package br.com.castgroup.cursos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_categoria")
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_categoria;
	
	@NotNull
	private String categoria;
	
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	

}
