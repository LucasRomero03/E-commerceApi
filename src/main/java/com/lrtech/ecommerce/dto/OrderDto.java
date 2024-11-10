package com.lrtech.ecommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.lrtech.ecommerce.entities.Order;
import com.lrtech.ecommerce.entities.OrderItem;
import com.lrtech.ecommerce.entities.enums.OrderStatus;

public class OrderDto {
  private Long id;
  private Instant moment;
  private OrderStatus status;

  private ClientDto client;

  private PaymentDto payment;

  private List<OrderItemDto> items = new ArrayList<>();

  public OrderDto(Long id, Instant moment, OrderStatus status, ClientDto client, PaymentDto payment) {
    this.id = id;
    this.moment = moment;
    this.status = status;
    this.client = client;
    this.payment = payment;
  }
  public OrderDto(Order e) {
    id = e.getId();
    moment = e.getMoment();
    status = e.getStatus();
    client = new ClientDto(e.getClient());
    payment = (e.getPayment()==null)? null: new PaymentDto(e.getPayment());
    for (OrderItem item : e.getItems()) {
       
        items.add(new OrderItemDto(item));
    }
  }
  public Long getId() {
    return id;
  }
  public Instant getMoment() {
    return moment;
  }
  public OrderStatus getStatus() {
    return status;
  }
  public ClientDto getClient() {
    return client;
  }
  public PaymentDto getPayment() {
    return payment;
  }
  public List<OrderItemDto> getItems() {
    return items;
  }

  public Double getTotal(){
    double sum = 0.0;
    for (OrderItemDto orderItemDto : items) {
      sum += orderItemDto.getSubTotal();
    }

    return sum;

    
  }
  public Object toList() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toList'");
  }
  
}
