package com.guifaleiros.comercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guifaleiros.comercial.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
