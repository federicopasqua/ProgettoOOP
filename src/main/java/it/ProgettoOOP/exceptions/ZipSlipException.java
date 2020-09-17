package it.ProgettoOOP.exceptions;

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
