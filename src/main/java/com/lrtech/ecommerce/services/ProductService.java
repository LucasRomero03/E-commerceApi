package com.lrtech.ecommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.dto.CategoryDto;
import com.lrtech.ecommerce.dto.ProductDto;
import com.lrtech.ecommerce.dto.ProductMinDto;
import com.lrtech.ecommerce.entities.Category;
import com.lrtech.ecommerce.entities.Product;
import com.lrtech.ecommerce.repositories.CategoryRepository;
import com.lrtech.ecommerce.repositories.ProductRepository;
import com.lrtech.ecommerce.services.exceptions.DataBaseException;
import com.lrtech.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductRepository productRepository;


  @Transactional(readOnly = true)
  public ProductDto getById( Long id) {
    
    Product product = productRepository.getById(id);
    return new ProductDto(product);

  }

  @Transactional(readOnly = true)
  public Page<ProductDto> findAll( Pageable pageable) {
    
    Page<Product> listProducts = productRepository.getAll(pageable);
    return listProducts.map(x -> new ProductDto(x));
   // meotdo personalizado usando o join feth
  }

  
  //metodo personalizado com jpql
  @Transactional(readOnly = true)
  public Page<ProductDto> searchByName(String name, Pageable pageable) {
  
    Page<Product> listProducts = productRepository.searchByName(name,pageable);
    return listProducts.map(x -> new ProductDto(x));

  }


  @Transactional
  public ProductDto insert (ProductDto productDto ) {
    Product product = new Product();
    dtoToEntity(product, productDto);
    for ( CategoryDto cat : productDto.getCategories()) {
      Category e = categoryRepository.getReferenceById(cat.getId());
      product.getCategories().add(e);
    }
    product = productRepository.save(product);

    return new ProductDto(product);
  }

  @Transactional
  public ProductDto update(Long id,ProductDto productDto ) {

    try {
     
    Product product = productRepository.getReferenceById(id);

    dtoToEntity(product, productDto);
    product.getCategories().clear();
    for ( CategoryDto cat : productDto.getCategories()) {
      Category e = categoryRepository.getReferenceById(cat.getId());
      product.getCategories().add(e);
    }
   
    
    
    product = productRepository.save(product);

    return new ProductDto(product); 
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Recurso não encontrado");
    }
  }

  private void dtoToEntity(Product product, ProductDto productDto){

    product.setName(productDto.getName());
    product.setDescription(productDto.getDescription());
    product.setPrice(productDto.getPrice());
    product.setImgUrl(productDto.getImgUrl());

    
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {

    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Recurso não encontrado");
    }
    try {
      productRepository.deleteById(id);  

    } catch (DataIntegrityViolationException e) {
      throw new DataBaseException("Falha na integridade referencial");
    }
    
  }

}
