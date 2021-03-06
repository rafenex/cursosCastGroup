package br.com.castgroup.cursos.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_log")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_log;
	private LocalDate inclusao;
	private LocalDate ultimaAtualizacao;
	private String acao;	
	
	@ManyToOne
	private Curso curso;

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;

	public Log(Integer id_log, LocalDate inclusao, LocalDate ultimaAtualizacao, Curso curso, String acao,
			Usuario usuario) {
		super();
		this.id_log = id_log;
		this.inclusao = inclusao;
		this.ultimaAtualizacao = ultimaAtualizacao;
		this.curso = curso;
		this.acao = acao;
		this.usuario = usuario;
	}
	
	public Log(Integer id_log, LocalDate ultimaAtualizacao, Curso curso, String acao,
			Usuario usuario) {
		super();
		this.id_log = id_log;
		this.ultimaAtualizacao = ultimaAtualizacao;
		this.curso = curso;
		this.acao = acao;
		this.usuario = usuario;
	}
	
	
	public Log() {
		// TODO Auto-generated constructor stub
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}


	public Integer getId_log() {
		return id_log;
	}

	public void setId_log(Integer id_log) {
		this.id_log = id_log;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public LocalDate getInclusao() {
		return inclusao;
	}

	public void setInclusao(LocalDate inclusao) {
		this.inclusao = inclusao;
	}

	public LocalDate getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
