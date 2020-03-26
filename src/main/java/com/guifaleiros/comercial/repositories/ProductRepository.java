package com.guifaleiros.comercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guifaleiros.comercial.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
