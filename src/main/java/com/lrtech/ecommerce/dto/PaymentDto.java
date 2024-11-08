package com.lrtech.ecommerce.dto;

import java.time.Instant;

import com.lrtech.ecommerce.entities.Payment;

public class PaymentDto {


  private Long id;
  private Instant moment;





  public PaymentDto(Payment e) {
    id = e.getId();
    moment = e.getMoment();
  }


  
  public PaymentDto(Long id, Instant moment) {
    this.id = id;
    this.moment = moment;
  }
  public PaymentDto() {
    
  }
  public Long getId() {
    return id;
  }
  public Instant getMoment() {
    return moment;
  }

  


}
