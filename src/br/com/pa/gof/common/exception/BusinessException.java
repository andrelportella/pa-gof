package br.com.pa.gof.common.exception;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException (String message) {
		super(message);
	}
	
	public BusinessException (String message, Exception exception) {
		super(message, exception);
	}

}
