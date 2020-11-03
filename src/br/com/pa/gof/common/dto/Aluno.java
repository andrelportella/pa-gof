package br.com.pa.gof.common.dto;

import java.io.Serializable;

public class Aluno extends EntityDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
