package it.ProgettoOOP.exceptions;

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
