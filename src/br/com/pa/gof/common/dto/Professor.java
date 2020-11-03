package br.com.pa.gof.common.dto;

import java.io.Serializable;
import java.util.Date;

public class Professor extends EntityDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataNascimento;
	private Integer numPrevSocial;
	private String departamento;
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getNumPrevSocial() {
		return numPrevSocial;
	}
	public void setNumPrevSocial(Integer numPrevSocial) {
		this.numPrevSocial = numPrevSocial;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
}
