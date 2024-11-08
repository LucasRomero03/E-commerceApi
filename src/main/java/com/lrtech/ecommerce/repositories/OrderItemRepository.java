package com.lrtech.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.ecommerce.entities.OrderItem;
import com.lrtech.ecommerce.entities.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem,OrderItemPk>{
  
}
