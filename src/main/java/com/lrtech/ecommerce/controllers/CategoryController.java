package com.lrtech.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrtech.ecommerce.dto.CategoryDto;
import com.lrtech.ecommerce.dto.ProductDto;
import com.lrtech.ecommerce.services.CategoryService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;



@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
  

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {


      return ResponseEntity.ok(categoryService.findById(id));
  }
  
  @GetMapping
  public ResponseEntity<List<CategoryDto>> findAll() {
      
    List<CategoryDto> result = categoryService.findAll();
    
    
    return ResponseEntity.ok(result);
  }
  
  @GetMapping(value = "/name")
  public ResponseEntity<List<CategoryDto>> getByName(@RequestParam(name = "name", defaultValue = "") String name) {
      
    List<CategoryDto> result= categoryService.findByName(name) ;
    
    
    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<CategoryDto> insert( @RequestBody CategoryDto dto) {
    dto = categoryService.postCategory(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }
  
  
}
