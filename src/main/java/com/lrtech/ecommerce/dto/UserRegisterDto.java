package com.lrtech.ecommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lrtech.ecommerce.entities.User;

public class UserRegisterDto {

  private Long userId;
  private String userName;
  private String phone;
  private String email;
  private LocalDate birthDate;
  private String password;

  private List<RoleDto> roles = new ArrayList<>();

  public UserRegisterDto(Long userId, String userName, String phone, String email, LocalDate birthDate, String password) {
    this.userId = userId;
    this.userName = userName;
    this.phone = phone;
    this.email = email;
    this.birthDate = birthDate;
    this.password = password;
  }

  public UserRegisterDto(User user) {
    userId = user.getUserId();
    userName = user.getUserName();
    phone = user.getPhone();
    email = user.getEmail();
    birthDate = user.getBirthDate();
    password = user.getPassword();
    roles = user.getRoles().stream().map(x -> new RoleDto(x)).collect(Collectors.toList());   
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

  public String getPassword() {
    return password;
  }

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }
  

}
