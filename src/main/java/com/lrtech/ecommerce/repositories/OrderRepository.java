package com.lrtech.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
  
}
