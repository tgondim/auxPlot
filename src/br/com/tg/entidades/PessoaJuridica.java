package br.com.tg.entidades;

import java.io.Serializable;
import java.util.Calendar;

public class PessoaJuridica extends Pessoa implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private Integer pessoaId;
	
	private String nomeFantasia;
	 
	private String cnpj;
	 
	private Calendar dataAbertura;
	 
	private short diaFatura;
	 
	public PessoaJuridica() {
	 
	}
	 
	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}
	 
	public void setNomeFantasia(String newNomeFantasia) {
		this.nomeFantasia = newNomeFantasia;
	 
	}
	 
	public String getCnpj() {
		return cnpj;
	}
	 
	public void setCnpj(String newCnpj) {
		this.cnpj = newCnpj;
	 
	}
	 
	public Calendar getDataAbertura() {
		return dataAbertura;
	}
	 
	public void setDataAbertura(Calendar newDataAbertura) {
		this.dataAbertura = newDataAbertura;
	 
	}
	 
	public short getDiaFatura() {
		return diaFatura;
	}
	 
	public void setDiaFatura(short newDiaFatura) {
		this.diaFatura = newDiaFatura;
	 
	}
	
}
 
