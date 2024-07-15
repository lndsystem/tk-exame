package com.exame.crud.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exame.crud.client.AddressClient;
import com.exame.crud.client.dto.AddressDTO;
import com.exame.crud.model.Address;
import com.exame.crud.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;
    private final AddressRepository addressRepository;

    public AddressDTO findByAddressByZipCode(final String zipCode) {
        final var result = this.addressClient.findAddressByZipCode(zipCode);
        return result.getResult();
    }

    public List<Address> findAllByClientId(Integer idClient) {
        return this.addressRepository.findByClientId(idClient);
    }

    public boolean isExistsAddress(Integer id) {
        return this.addressRepository.existsById(id);
    }

    @Modifying
    @Transactional
    public void removeAddress(Integer id) {
        if (isExistsAddress(id)) {
            this.addressRepository.deleteById(id);
        }
    }

}
