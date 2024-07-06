package com.lrtech.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

  
  
}
