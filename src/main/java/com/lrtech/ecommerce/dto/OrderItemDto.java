package com.lrtech.ecommerce.dto;

import com.lrtech.ecommerce.entities.OrderItem;

public class OrderItemDto {
  private Long productId;
  private String name;
  private Double price;
  private Integer quantity;
  private String imgUrl;








  
  public OrderItemDto(Long productId, String name, Double price, Integer quantity,String imgUrl) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.imgUrl = imgUrl;
  }
  public OrderItemDto(OrderItem e) {
    productId = e.getProduct().getId();
    name = e.getProduct().getName();
    price = e.getPrice();
    quantity = e.getQuantity();
    imgUrl = e.getProduct().getImgUrl();
  }

  public Double getSubTotal(){
    return price*quantity;
  }
  public Long getProductId() {
    return productId;
  }
  public String getName() {
    return name;
  }
  public Double getPrice() {
    return price;
  }
  public Integer getQuantity() {
    return quantity;
  }
  public String getImgUrl() {
    return imgUrl;
  }



  


}
