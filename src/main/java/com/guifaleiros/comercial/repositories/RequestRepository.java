package com.guifaleiros.comercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guifaleiros.comercial.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

}
