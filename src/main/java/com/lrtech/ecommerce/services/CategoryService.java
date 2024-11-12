package com.lrtech.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.dto.CategoryDto;
import com.lrtech.ecommerce.entities.Category;
import com.lrtech.ecommerce.repositories.CategoryRepository;
import com.lrtech.ecommerce.services.exceptions.ResourceNotFoundException;



@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;


  @Transactional(readOnly = true)
  public List<CategoryDto> findAll(){
    List<Category> result = categoryRepository.findAll();
    

    return result.stream().map(x -> new CategoryDto(x)).toList();
  }
  @Transactional(readOnly = true)
  public List<CategoryDto> findByName(String name){
    List<Category> result = categoryRepository.findByName(name);

    
    
    return result.stream().map(x -> new CategoryDto(x)).toList();
  }

  @Transactional
  public CategoryDto findById(Long id){
    Category entity = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("recurso nao encontrado"));
    CategoryDto dto = new CategoryDto(entity.getId(),entity.getName());
    
    return dto;
  }

  @Transactional
  public CategoryDto postCategory(CategoryDto dto){
    Category cat = new Category();
    cat.setName(dto.getName());
    
    categoryRepository.save(cat);
    return new CategoryDto(cat);
  }

  

}
