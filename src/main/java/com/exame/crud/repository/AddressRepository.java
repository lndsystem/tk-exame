package com.exame.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exame.crud.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	List<Address> findByClientId(Integer idClient);

}
