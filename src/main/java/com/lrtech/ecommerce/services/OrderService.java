package com.lrtech.ecommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.dto.OrderDto;
import com.lrtech.ecommerce.dto.OrderItemDto;
import com.lrtech.ecommerce.entities.Order;
import com.lrtech.ecommerce.entities.OrderItem;
import com.lrtech.ecommerce.entities.OrderItemPk;
import com.lrtech.ecommerce.entities.Product;
import com.lrtech.ecommerce.entities.User;
import com.lrtech.ecommerce.entities.enums.OrderStatus;
import com.lrtech.ecommerce.repositories.OrderItemRepository;
import com.lrtech.ecommerce.repositories.OrderRepository;
import com.lrtech.ecommerce.repositories.ProductRepository;
import com.lrtech.ecommerce.services.exceptions.DataBaseException;
import com.lrtech.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

  @Autowired
  private OrderRepository repository;
  @Autowired
  private OrderItemRepository orderItemRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private AuthService authService;

  @Autowired
  private OrderItemService orderItemService;

  @Transactional(readOnly = true)
  public OrderDto findById(Long id) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

    authService.validateSelfOrAdmin(order.getClient().getUserId());

    return new OrderDto(order);

  }
  @Transactional(readOnly = true)
  private Order findForDelete(Long id) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

   

    return order;

  }

  @Transactional
  public OrderDto insert(OrderDto dto) {
    Order order = new Order();
    order.setMoment(Instant.now());
    order.setStatus(OrderStatus.WAITING_PAYMENT);
    User user = userService.authenticated();
    order.setClient(user);
    for (OrderItemDto item : dto.getItems()) {
      Product product = productRepository.getReferenceById(item.getProductId());
      OrderItem orderItem = new OrderItem(order, product, item.getQuantity(), product.getPrice());
      order.getItems().add(orderItem);

    }

    repository.save(order);
    // tem que salvar por ser um relacionamento de terceiros
    orderItemRepository.saveAll(order.getItems());

    return new OrderDto(order);
  }
  
  @Transactional
  public OrderDto update(Long id, OrderDto dto) {

      Order order = repository.getReferenceById(id);
      
      
     
     // order.setMoment(Instant.now());
      order.setStatus(OrderStatus.WAITING_PAYMENT);


     // User user = userService.authenticated();
     // order.setClient(user);
     orderItemService.deleteByOrderId(id);
    // orderItemRepository.deleteByOrderId(order.getId()); //metodo personalizado
      //order.getItems().clear();  -> tbm funciona 
      repository.save(order);  // quando for entidade que mexe com outras tabelas persistir primeiro a entidade e pds mexe no resto
     
     
      for (OrderItemDto item : dto.getItems()) {

        
        Product product = productRepository.getReferenceById(item.getProductId());
        OrderItem orderItem = new OrderItem(order, product, item.getQuantity(), product.getPrice());
        order.getItems().add(orderItem);

      }
      
     
      // tem que salvar por ser um relacionamento de terceiros
      orderItemRepository.saveAll(order.getItems());

      return new OrderDto(order);

   


  }

  @Transactional(readOnly = true)
  public Page<OrderDto> getAll(Pageable pageable) {

    Page<Order> result1 = repository.findAll(pageable);

    return result1.map(x -> new OrderDto(x));

  }
  @Transactional()
  public void delete(Long id) {

    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Recurso não encontrado");
    }
    try {
      // Remover todos os itens do pedido -> modo correto de usar 
      orderItemService.deleteByOrderId(id);
       // orderItemRepository.deleteByOrderId(id);
      // Excluir o pedido
      repository.deleteById(id);

    } catch (DataIntegrityViolationException e) {
      throw new DataBaseException("Falha na integridade referencial ao tentar excluir o pedido");
    }
    
  }


}
