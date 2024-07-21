package com.exame.crud.repository.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ClientDropdownProjection {
	Integer getId();

	String getFistName();

	String getLastName();

	@Value("#{target.firstName + ' ' + target.lastName}")
	String getFullName();
}
