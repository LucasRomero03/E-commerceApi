package com.lrtech.ecommerce.dto;

import com.lrtech.ecommerce.entities.User;

public class ClientDto {
  private Long id;
  private String name;



  public ClientDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  public ClientDto(User user) {
    this.id = user.getUserId();
    this.name = user.getUserName();
  }
  public ClientDto() {
  }
  public Long getId() {
    return id;
  }
  public String getName() {
    return name;
  }

  
}
