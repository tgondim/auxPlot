package br.com.tg.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


/**
 * Representa todas as pessoas no sistema.
 * Física, jurídica, usuário.
 */

public abstract class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	 
	private Endereco endereco;

	private List<Telefone> telefones;
	
	private StatusPessoa statusPessoa;

	private TipoPessoa tipoPessoa;

	private Calendar dataCadastro;

	private Usuario usuarioCadastro;

	private Calendar dataAlteracao;

	private Usuario usuarioAlteracao;
	 
	public Pessoa() {
	 
	}
	 
	public Integer getId() {
		return id;
	}
	 
	public void setId(Integer newId) {
		this.id = newId;
	 
	}
	 
	public String getNome() {
		return nome;
	}
	 
	public void setNome(String newNome) {
		this.nome = newNome;
	 
	}
	 
	public Endereco getEndereco() {
		return endereco;
	}
	 
	public void setEndereco(Endereco newEndereco) {
		this.endereco = newEndereco;
	 
	}
	 
	public List<Telefone> getTelefones() {
		return telefones;
	}
	 
	public void setTelefones(List<Telefone> newTelefones) {
		this.telefones = newTelefones;
	 
	}
	 
	public StatusPessoa getStatusPessoa() {
		return statusPessoa;
	}
	 
	public void setStatusPessoa(StatusPessoa newStatusPessoa) {
		this.statusPessoa = newStatusPessoa;
	 
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}
 
