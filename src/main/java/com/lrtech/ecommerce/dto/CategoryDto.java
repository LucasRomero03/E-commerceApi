package com.lrtech.ecommerce.dto;

import com.lrtech.ecommerce.entities.Category;

public class CategoryDto {

  private Long id;
  private String name;


  public CategoryDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }


  public CategoryDto() {
  }


  public CategoryDto(Category e) {
    id = e.getId();
    name = e.getName();
  
  }


  public Long getId() {
    return id;
  }


  public String getName() {
    return name;
  }


  
  

}
