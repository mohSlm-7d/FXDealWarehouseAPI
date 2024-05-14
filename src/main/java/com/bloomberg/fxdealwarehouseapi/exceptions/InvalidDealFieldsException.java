package com.bloomberg.fxdealwarehouseapi.exceptions;

public class InvalidDealFieldsException extends RuntimeException {
	public InvalidDealFieldsException() {
		super("Some fields of the FX Deal are not valid.");
	}
	
	
	
	public InvalidDealFieldsException(String message) {
		super(message);
	}
	
	public InvalidDealFieldsException(Throwable cause) {
		super(cause);
	}
	
	public InvalidDealFieldsException(String message, Throwable cause) {
		super(message, cause);
	}
}
