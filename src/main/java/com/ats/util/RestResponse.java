package com.ats.util;



public class RestResponse {
	private final boolean success;
	private final String message;
	private final Object dto;

	public RestResponse(boolean success, String message, Object dto) {
		super();
		this.success = success;
		this.message = message;
		this.dto = dto;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public Object getDto() {
		return dto;
	}

}
