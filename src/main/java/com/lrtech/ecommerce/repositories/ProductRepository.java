package com.lrtech.ecommerce.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lrtech.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // devido ao like tem q fazer a maracutaia do concat;

  @Query(" SELECT DISTINCT obj FROM Product obj " +
      " JOIN FETCH obj.categories cat " +
      " WHERE (UPPER(obj.name) LIKE UPPER(CONCAT('%', :name , '%')) ) OR  (UPPER(cat.name) LIKE UPPER(CONCAT('%', :catname , '%')))")
  Page<Product> searchByNameAndCategories(String name,String catname,Pageable pageable);

  @Query(value = " SELECT obj FROM Product obj JOIN FETCH obj.categories ")
  Page<Product> getAll(Pageable pageable);

  @SuppressWarnings("null")
  @Query(value = " SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj.id = :id")
  Product getById(Long id);

}
