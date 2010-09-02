package br.com.tg.entidades;

public class TipoTelefone {
 
	private Integer id;
	 
	private String descricao;
	 
	public TipoTelefone() {
		this.id = 0;
		this.descricao = "";
	}
	
	public TipoTelefone(int newId, String newDescricao) {
		this.id = newId;
		this.descricao = newDescricao;
	}
	 
	public Integer getId() {
		return id;
	}
	 
	public void setId(Integer newId) {
		this.id = newId;
	 
	}
	 
	public String getDescricao() {
		return descricao;
	}
	 
	public void setDescricao(String newDescricao) {
		this.descricao = newDescricao;
	 
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
	 
}
 
