package com.lrtech.ecommerce.dto;

import com.lrtech.ecommerce.entities.Product;

public class ProductMinDto {

  private Long id;
  private String name;

  private Double price;
  private String imgUrl;

  public ProductMinDto(Long id, String name, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductMinDto(Product product) {
    id = product.getId();
    name = product.getName();
    price = product.getPrice();
    imgUrl = product.getImgUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

 
  public Double getPrice() {
    return price;
  }

  public String getImgUrl() {
    return imgUrl;
  }

}
