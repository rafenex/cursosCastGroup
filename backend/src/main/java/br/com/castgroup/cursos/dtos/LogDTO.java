package br.com.castgroup.cursos.dtos;

import java.time.LocalDate;

public class LogDTO {
	private String nome;
	private String descricao;
	private Integer id_log;
	private String acao;
	private LocalDate inclusao;
	private LocalDate ultimaAtualizacao;


	public LogDTO(LocalDate inclusao, LocalDate ultimaAtualizacao,String nome, String descricao, Integer id_log, String acao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.acao = acao;
		this.inclusao = inclusao;
		this.ultimaAtualizacao = ultimaAtualizacao;
		this.id_log = id_log;
	}

	

	public LogDTO() {

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



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId_log() {
		return id_log;
	}

	public void setId_log(Integer id_log) {
		this.id_log = id_log;
	}




	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}


	
	
	
	

}
