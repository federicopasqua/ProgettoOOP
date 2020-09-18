package it.ProgettoOOP.exceptions;

/**
 * Errore che si verifica quando si cerca di visualizzare una cartella non esistente
 *  o di cui non si ha i permessi.
 */
public class InvalidPathException extends Exception{
	String msg;
	
	public InvalidPathException(String msg) {
		super();
		this.msg = msg;
	}
	
	
	public String getMessage() {
		return msg;
	}
}
