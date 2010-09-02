package br.com.tg.entidades;


public class Endereco {
 
	private Integer id;
	
	private Pessoa pessoa;
	
	private String logradouro;
	 
	private Integer numero;
	 
	private String bairro;
	
	private String complemento;
	 
	private String cidade;
	 
	private String uf;
	 
	private String cep;
	 
	private String email;
	 
	public Endereco() {
	 
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

	public String getLogradouro() {
		return logradouro;
	}
	 
	public void setLogradouro(String newLogradouro) {
		this.logradouro = newLogradouro;
	 
	}
	 
	public Integer getNumero() {
		return numero;
	}
	 
	public void setNumero(Integer newNumero) {
		this.numero = newNumero;
	 
	}
	 
	public String getBairro() {
		return bairro;
	}
	 
	public void setBairro(String newBairro) {
		this.bairro = newBairro;
	 
	}
	 
	public String getComplemento() {
		return complemento;
	}
	 
	public void setComplemento(String newComplemento) {
		this.complemento = newComplemento;
	 
	}
	 
	public String getCidade() {
		return cidade;
	}
	 
	public void setCidade(String newCidade) {
		this.cidade = newCidade;
	 
	}
	 
	public String getUf() {
		return uf;
	}
	 
	public void setUf(String newUf) {
		this.uf = newUf;
	 
	}
	 
	public String getCep() {
		return cep;
	}
	 
	public void setCep(String newCep) {
		this.cep = newCep;
	 
	}
	 
	public String getEmail() {
		return email;
	}
	 
	public void setEmail(String newEmail) {
		this.email = newEmail;
	 
	}
	 
}
 
