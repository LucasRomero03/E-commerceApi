package com.lrtech.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lrtech.ecommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {
  
  private Long id;
  @Size(min= 3, max = 80, message = "Nome precisa ter entre 3 a 80 caracteres")
  @NotBlank(message = "Campo Obrigatório")
  private String name;
  
  @Size(min= 10,message = "Descricao tem que ter no minimo 10 caracteres")
  @NotBlank(message = "Campo Obrigatório")
  private String description;
  
  @Positive(message = "Preço não pode ser negativo")
  private Double price;
  private String imgUrl;

@NotEmpty(message = "deve ter pelo menos 1 categoria")
  private List<CategoryDto> categories = new ArrayList<>();


  

  public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductDto(Product product) {
    id = product.getId();
    name = product.getName();
    description = product.getDescription();
    price = product.getPrice();
    imgUrl = product.getImgUrl();
    categories= product.getCategories().stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
    
  }


  

  public Long getId() {
    return id;
  }


  public String getName() {
    return name;
  }


  public String getDescription() {
    return description;
  }


  public Double getPrice() {
    return price;
  }


  public String getImgUrl() {
    return imgUrl;
  }

  public List<CategoryDto> getCategories() {
    return categories;
  }
  
  
}
