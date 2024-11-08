package com.lrtech.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrtech.ecommerce.dto.CategoryDto;
import com.lrtech.ecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
  

  @Autowired
  private CategoryService categoryService;

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
  
  
}
