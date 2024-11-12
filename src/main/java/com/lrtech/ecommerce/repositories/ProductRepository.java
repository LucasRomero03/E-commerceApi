package com.lrtech.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lrtech.ecommerce.entities.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {
  //devido ao like tem q fazer a maracutaia do concat;

  @Query(value = " SELECT obj FROM Product obj JOIN FETCH obj.categories " +
  " WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name , '%'))" )
  Page<Product> searchByName(String name,Pageable pageable);

  @Query(value = " SELECT obj FROM Product obj JOIN FETCH obj.categories ")
  Page<Product> getAll(Pageable pageable);
  
  
  @SuppressWarnings("null")
  @Query(value = " SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj.id = :id")
  Product getById(Long id);

}
