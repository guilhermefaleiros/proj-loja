package com.guifaleiros.comercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifaleiros.comercial.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
