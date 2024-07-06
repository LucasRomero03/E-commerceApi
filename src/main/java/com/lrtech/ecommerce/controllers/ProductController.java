package com.lrtech.ecommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrtech.ecommerce.dto.ProductDto;
import com.lrtech.ecommerce.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductDto> findById(@PathVariable Long id) {

    ProductDto productDto=  productService.findById(id);

    return ResponseEntity.ok(productDto);

  }

  @GetMapping
  public ResponseEntity <Page<ProductDto>> findAll(Pageable pageable) {

    Page<ProductDto> productDto = productService.findAll(pageable);

    return ResponseEntity.ok(productDto);
  }

  @PostMapping
  public ResponseEntity <ProductDto> insert(@Valid @RequestBody ProductDto productDto) {
      productDto= productService.insert(productDto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDto.getId()).toUri();
      return ResponseEntity.created(uri).body(productDto);
  }
  
  @PutMapping(value = "/{id}")
  public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {

    productDto=  productService.update(id, productDto);

    return ResponseEntity.ok(productDto);

  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {

    productService.delete(id);
    return ResponseEntity.noContent().build();

  }
}
