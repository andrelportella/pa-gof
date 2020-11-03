package br.com.pa.gof.common.dto;

public class EntityDto {
	
	public static final String ATIVO = "A";
	public static final String INATIVO = "I";
	
	private Integer id;
	private String nome;
	private String status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
