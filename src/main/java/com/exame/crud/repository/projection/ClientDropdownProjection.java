package com.exame.crud.repository.projection;

public interface ClientDropdownProjection {
	Integer getId();

	String getFirstName();

	String getLastName();

	default String getFullName() {
		return getFirstName() + " " + getLastName();
	}
}
