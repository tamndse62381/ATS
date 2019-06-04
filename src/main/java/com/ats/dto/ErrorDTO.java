package com.ats.dto;

public class ErrorDTO {
	String message;
	String codeError;
	
	public ErrorDTO() {
		super();
	}


	public ErrorDTO(String message, String codeError) {
		super();
		this.message = message;
		this.codeError = codeError;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getCodeError() {
		return codeError;
	}


	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}
	
	
}
