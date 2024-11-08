package com.lrtech.ecommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.lrtech.ecommerce.entities.User;

public class UserDto {
  
  
  private Long userId;
  private String userName;
  private String phone;
  private String email;
  private LocalDate birthDate;

  private List<String> roles = new ArrayList<>();



  
  public UserDto(Long userId, String userName, String phone, String email, LocalDate birthDate, List<String> roles) {
    this.userId = userId;
    this.userName = userName;
    this.phone = phone;
    this.email = email;
    this.birthDate = birthDate;
    this.roles = roles;
  }

  public UserDto(User user) {
    userId = user.getUserId();
    userName = user.getUserName();
    phone = user.getPhone();
    email = user.getEmail();
    birthDate = user.getBirthDate();
    for (GrantedAuthority role : user.getAuthorities()) {
      roles.add(role.getAuthority());
    }
  }
  

  public UserDto() {
  }

  public Long getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public List<String> getRoles() {
    return roles;
  }

  

}
