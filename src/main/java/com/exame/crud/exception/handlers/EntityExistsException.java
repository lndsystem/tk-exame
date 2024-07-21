package com.exame.crud.exception.handlers;

import lombok.Getter;

@Getter
public class EntityExistsException extends RuntimeException {

	private static final long serialVersionUID = 1246797797435153622L;

	private String name;
	private String message;

	public EntityExistsException(String name, String message) {
		this.name = name;
		this.message = message;
	}
}
