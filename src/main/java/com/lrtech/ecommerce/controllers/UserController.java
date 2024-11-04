package com.lrtech.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrtech.ecommerce.dto.UserDto;
import com.lrtech.ecommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
  @GetMapping(value = "/me")
  public ResponseEntity<UserDto> getMe() {

    UserDto userDto = userService.getMe();

    return ResponseEntity.ok(userDto);

  }

  /*
   * @GetMapping padrao
   * public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable) {
   * 
   * Page<ProductDto> productDto = productService.findAll(pageable);
   * 
   * return ResponseEntity.ok(productDto);
   * }
   */

 
}
