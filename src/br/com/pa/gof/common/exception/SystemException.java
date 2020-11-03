package br.com.pa.gof.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DATABASE = "DATABASE";
	
	private Exception exception;
	private String cause;
	
	/**
	 * Cria uma exceção representando um erro
	 * @param message Mensagem de erro
	 * @param exception Exceção original
	 * @return
	 */
	public static SystemException dataBase(Exception exception) {
		return new SystemException(exception, DATABASE);
	}
	
	private SystemException(Exception exception, String cause) {		
		super(exception);
		this.exception = exception;
		this.cause = cause;
	}
	
	@Override
	public String getMessage() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return cause+":\n"+pw.toString();
	}
	

}
