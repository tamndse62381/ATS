package com.ats.util;

import com.ats.dto.AccountDTO;

public class RestResponse {
	private final boolean success;
	private final String message;
	private final AccountDTO dto;


	public RestResponse(boolean success, String message, AccountDTO dto) {
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


	public AccountDTO getDto() {
		return dto;
	}

	
}
