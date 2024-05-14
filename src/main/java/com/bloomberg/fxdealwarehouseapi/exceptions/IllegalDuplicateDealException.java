package com.bloomberg.fxdealwarehouseapi.exceptions;

public class IllegalDuplicateDealException extends RuntimeException{
	public IllegalDuplicateDealException() {
		super("Violation of Unique Constraint. The Deal ID must be unique. This FX Deal with the same Deal ID already exists. Duplicates are not allowed.");
	}
	
	
	
	public IllegalDuplicateDealException(String message) {
		super(message);
	}
	
	public IllegalDuplicateDealException(Throwable cause) {
		super(cause);
	}
	
	public IllegalDuplicateDealException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
}
