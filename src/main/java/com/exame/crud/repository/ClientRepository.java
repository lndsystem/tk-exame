package com.exame.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exame.crud.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	boolean existsByEmail(final String email);
	
}
