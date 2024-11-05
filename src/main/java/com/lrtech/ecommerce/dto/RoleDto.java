package com.lrtech.ecommerce.dto;

import com.lrtech.ecommerce.entities.Role;

public class RoleDto {
  private Long id;
  private String authority;

  



  
  public RoleDto(Long id, String authority) {
    this.id = id;
    this.authority = authority;
  }
  public RoleDto(Role role){
    id= role.getId();
    authority = role.getAuthority();
  }
 
  public Long getId() {
    return id;
  }
  public String getAuthority() {
    return authority;
  }



  
}
