package br.com.tg.entidades;


public class Telefone {
 
	private Integer id;
	
	private Pessoa pessoa;
	
	private TipoTelefone tipoTelefone;
	 
	private String codigoArea;
	 
	private String numero;
	 
	public Telefone() {
		this.id = 0;
		this.codigoArea = "";
		this.numero = "";
		this.tipoTelefone = new TipoTelefone();
	}
	 
	public Telefone(int newId, String newCodigoArea, String newNumero, TipoTelefone newTipoTelefone) {
		this.id = newId;
		this.codigoArea = newCodigoArea;
		this.numero = newNumero;
		this.tipoTelefone = newTipoTelefone;
	}

	public Integer getId() {
		return id;
	}
	 
	public void setId(Integer newId) {
		this.id = newId;
	 
	}
	 
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}
	 
	public void setTipoTelefone(TipoTelefone newTipoTelefone) {
		this.tipoTelefone = newTipoTelefone;
	 
	}
	 
	public String getCodigoArea() {
		return codigoArea;
	}
	 
	public void setCodigoArea(String newCodigoArea) {
		this.codigoArea = newCodigoArea;
	 
	}
	 
	public String getNumero() {
		return numero;
	}
	 
	public void setNumero(String newNumero) {
		this.numero = newNumero;
	 
	}
	 
}
 
