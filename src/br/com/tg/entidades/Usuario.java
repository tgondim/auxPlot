package br.com.tg.entidades;

import java.io.Serializable;


public class Usuario extends Pessoa implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private Integer pessoaId;

	private String login;
	
	private String senha;
	 
	private TipoUsuario tipoUsuario;
	 
	public Usuario() {
	 
	}
	 
	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getLogin() {
		return login;
	}
	 
	public void setLogin(String newLogin) {
		this.login = newLogin;
	 
	}
	 
	public String getSenha() {
		return senha;
	}
	 
	public void setSenha(String newSenha) {
		this.senha = newSenha;
	 
	}
	 
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	 
	public void setTipoUsuario(TipoUsuario newTipoUsuario) {
		this.tipoUsuario = newTipoUsuario;
	 
	}
	
	@Override
	public String toString() {
		return getLogin();
	}
	 
}
 
