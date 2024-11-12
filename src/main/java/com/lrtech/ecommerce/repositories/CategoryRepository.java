package com.lrtech.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lrtech.ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
  

  @Query(value = "SELECT obj FROM Category obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%',:name,'%'))")
  List<Category> findByName(String name);

  
}
