package br.com.tg.entidades;

import java.io.Serializable;
import java.util.Calendar;

public class PessoaFisica extends Pessoa implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private Integer pessoaId;
	
	private String cpf;
	 
	private Calendar dataNascimento;

	public PessoaFisica() {
	 
	}
	
	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getCpf() {
		return cpf;
	}
	 
	public void setCpf(String newCpf) {
		this.cpf = newCpf;
	 
	}
	 
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	 
	public void setDataNascimento(Calendar newDataNascimento) {
		this.dataNascimento = newDataNascimento;
	 
	}
	
}
 
