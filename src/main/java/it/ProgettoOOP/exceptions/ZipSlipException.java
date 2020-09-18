package it.ProgettoOOP.exceptions;

/**
 * Errore che si verifica nel caso in cui si cerca di sfruttare lo zip slip per modificare file
 * di cui non si ha i permessi. 
 */
public class ZipSlipException extends Exception{
	String msg;
	
	public ZipSlipException(String msg) {
		super();
		this.msg = msg;
	}
	
	
	public String getMessage() {
		return msg;
	}
}
