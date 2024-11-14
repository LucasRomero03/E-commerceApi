package com.lrtech.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lrtech.ecommerce.entities.OrderItem;
import com.lrtech.ecommerce.entities.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
  
  @Modifying
  @Query("DELETE FROM OrderItem oi WHERE oi.id.order.id = :orderId")
  void deleteByOrderId(Long orderId);

}
