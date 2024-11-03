package com.lrtech.ecommerce.services;


import com.lrtech.ecommerce.services.exceptions.*;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.dto.ProductDto;
import com.lrtech.ecommerce.entities.Product;
import com.lrtech.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {


  @Autowired
  private ProductRepository productRepository;
  @Transactional(readOnly = true)
  public ProductDto findById( Long id) {
    
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    return new ProductDto(product);

  }

 /*  @Transactional(readOnly = true)
  public Page<ProductDto> findAll( Pageable pageable) {
    
    Page<Product> listProducts = productRepository.findAll(pageable);
    return listProducts.map(x -> new ProductDto(x));
    esse aq é o metodo usando o finda all normal do repository
  }*/
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
    product = productRepository.save(product);

    return new ProductDto(product);
  }

  @Transactional
  public ProductDto update(Long id,ProductDto productDto ) {

    try {
     
    Product product = productRepository.getReferenceById(id);

    dtoToEntity(product, productDto);
    
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
