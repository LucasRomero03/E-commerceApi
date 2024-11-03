package com.lrtech.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lrtech.ecommerce.dto.UserDetailsDTO;
import com.lrtech.ecommerce.entities.Role;
import com.lrtech.ecommerce.entities.User;
import com.lrtech.ecommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
    List<UserDetailsDTO> result1 = userRepository.searchUserAndRolesByEmail1(username);
    
    if (result1.size()==0) {
      throw new UsernameNotFoundException("user not found");
    }

    User user = new User();
    user.setEmail(username);
    user.setPassword(result1.get(0).getPassword());
    for (UserDetailsDTO projection : result1) {
        user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
    }

    return user;
  }
  
}
