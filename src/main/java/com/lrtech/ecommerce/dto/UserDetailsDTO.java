package com.lrtech.ecommerce.dto;

//esta parado pois iria tentar fazer sem usar o sql raiz quando puder testar voce faz, por enquanto nao teste vai perder tempo
public class UserDetailsDTO {

  private String email;
  private String password;
  private Long roleId;
  private String authority;

  



  public UserDetailsDTO(String email, String password, Long roleId, String authority) {
    this.email = email;
    this.password = password;
    this.roleId = roleId;
    this.authority = authority;
  }
 

  public UserDetailsDTO() {
  }
 
  public String getPassword() {
    return password;
  }
  public Long getRoleId() {
    return roleId;
  }
  public String getAuthority() {
    return authority;
  }
  public String getUserName() {
    return email;
  }


  @Override
  public String toString() {
    return "UserDetailsDTO [userName=" + email + ", password=" + password + ", roleId=" + roleId + ", authority="
        + authority + "]";
  }


  

}
