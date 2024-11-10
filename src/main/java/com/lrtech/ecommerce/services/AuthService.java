package com.lrtech.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrtech.ecommerce.entities.User;
import com.lrtech.ecommerce.services.exceptions.ForbidenException;

@Service
public class AuthService {
  



  @Autowired
  private UserService userService;



  public void validateSelfOrAdmin(Long userId){

    User me = userService.authenticated();

    if (!me.hasRole("ROLE_ADMIN") && !me.getUserId().equals(userId)  ) {
      throw new ForbidenException("acesso negado ");
    }
    

    
  }

}
