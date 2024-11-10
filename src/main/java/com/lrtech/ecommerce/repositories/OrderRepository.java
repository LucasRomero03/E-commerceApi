package com.lrtech.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lrtech.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

  @Query(" SELECT obj FROM Order obj " +
           " JOIN FETCH obj.client " +
           " JOIN FETCH obj.payment " +
           " JOIN FETCH obj.items item " +
           " JOIN FETCH obj.items.id.product")
  Page<Order> getAll(Pageable pageable);

  
}
