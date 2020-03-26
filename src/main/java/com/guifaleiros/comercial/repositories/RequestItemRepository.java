package com.guifaleiros.comercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guifaleiros.comercial.models.RequestItem;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {

}
