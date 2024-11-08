package com.lrtech.ecommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrtech.ecommerce.dto.OrderDto;
import com.lrtech.ecommerce.services.OrderService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  
  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDto> findById(@PathVariable Long id) {

    OrderDto dto=  orderService.findById(id);

    return ResponseEntity.ok(dto);

  }

  @PostMapping
  public ResponseEntity <OrderDto> insert(@Valid @RequestBody OrderDto dto) {
      dto= orderService.insert(dto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
      return ResponseEntity.created(uri).body(dto);
  }

  /*@GetMapping padrao
  public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable) {

    Page<ProductDto> productDto = productService.findAll(pageable);

    return ResponseEntity.ok(productDto);
  }*/
/*
  @GetMapping
  public ResponseEntity<Page<ProductMinDto>> findAll(@RequestParam(name = "name", defaultValue = "") String name,Pageable pageable) {

    Page<ProductMinDto> productDto = productService.searchByName(name,pageable);

    return ResponseEntity.ok(productDto);
  }

  
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity <ProductDto> insert(@Valid @RequestBody ProductDto productDto) {
      productDto= productService.insert(productDto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDto.getId()).toUri();
      return ResponseEntity.created(uri).body(productDto);
  }
  
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping(value = "/{id}")
  public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {

    productDto=  productService.update(id, productDto);

    return ResponseEntity.ok(productDto);

  }
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {

    productService.delete(id);
    return ResponseEntity.noContent().build();

  }
     */
}
