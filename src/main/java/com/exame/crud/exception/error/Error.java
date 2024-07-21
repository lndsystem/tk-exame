package com.exame.crud.exception.error;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Error {
	private String error;
	private String message;
	private String detail;
	private List<Fields> fieldErrors;

	public Error(String error, String message, String detail, List<Fields> fieldErrors) {
		super();
		this.error = error;
		this.message = message;
		this.detail = detail;
		this.fieldErrors = fieldErrors;
	}

	public Error(String error, String message, String detail) {
		super();
		this.error = error;
		this.message = message;
		this.detail = detail;
	}

}
