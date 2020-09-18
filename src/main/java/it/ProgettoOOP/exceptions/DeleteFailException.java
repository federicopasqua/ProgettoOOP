package it.ProgettoOOP.exceptions;

/**
 * Errore che si verifica nel caso non sia stato possibile eliminare una cartella in locale.
 */
public class DeleteFailException extends Exception{

	String msg;
	
	public DeleteFailException(String msg) {
		super();
		this.msg = msg;
	}
	
	
	public String getMessage() {
		return msg;
	}

}
