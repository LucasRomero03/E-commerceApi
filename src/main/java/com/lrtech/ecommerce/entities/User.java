package com.lrtech.ecommerce.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;



@Entity
@Table(name ="tb_user")

public class User {
  
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Long userId;
  private String userName;
  private String password;
  private String phone;
  @Column(unique = true)
  private String email;
  private LocalDate birthDate;


  @OneToMany(mappedBy = "client")
  private List<Order> orders = new ArrayList<Order>();



  
  


  public User(Long userId, String userName, String password, String phone, String email, LocalDate birthDate) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.phone = phone;
    this.email = email;
    this.birthDate = birthDate;
  }


  public User() {
  }


  public Long getUserId() {
    return userId;
  }


  public String getUserName() {
    return userName;
  }


  public String getPassword() {
    return password;
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


  public void setUserId(Long userId) {
    this.userId = userId;
  }


  public void setUserName(String userName) {
    this.userName = userName;
  }


  public void setPassword(String password) {
    this.password = password;
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


  public List<Order> getOrders() {
    return orders;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }

  
  
  
  
}
