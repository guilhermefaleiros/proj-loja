package com.guifaleiros.comercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guifaleiros.comercial.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
