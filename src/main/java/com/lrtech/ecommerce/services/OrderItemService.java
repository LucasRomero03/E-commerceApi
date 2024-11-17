package com.lrtech.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.repositories.OrderItemRepository;

@Service
public class OrderItemService {
  
  @Autowired
  private OrderItemRepository repository;

  
  //diferença entre suports e required 
  @Transactional(propagation = Propagation.SUPPORTS)
  public void deleteByOrderId(Long id){

    
    repository.deleteByOrderId(id);

    
  }
}
