package com.lrtech.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrtech.ecommerce.dto.UserDto;
import com.lrtech.ecommerce.dto.UserRegisterDto;
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
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {

    List< UserDto> result = userService.getAllUsers();

    return ResponseEntity.ok(result);

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

@PostMapping
  public ResponseEntity <UserRegisterDto> registerMe(@RequestBody UserRegisterDto userRegisterDto) {
      userRegisterDto= userService.registerMe(userRegisterDto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userRegisterDto.getUserId()).toUri();
      return ResponseEntity.created(uri).body(userRegisterDto);
  }
  
   
 
}
