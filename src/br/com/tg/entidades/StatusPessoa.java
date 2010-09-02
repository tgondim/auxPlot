package br.com.tg.entidades;

import java.io.Serializable;

public class StatusPessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}

}
