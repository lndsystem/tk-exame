package com.exame.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exame.crud.model.Client;
import com.exame.crud.repository.projection.ClientDropdownProjection;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	boolean existsByEmail(final String email);

	@Query(value = "select c.id as id, c.firstName as firstName, c.lastName as lastName from Client c order by c.firstName")
	List<ClientDropdownProjection> findAllClients();

}
