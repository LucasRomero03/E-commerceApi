package com.lrtech.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.dto.UserDetailsDTO;
import com.lrtech.ecommerce.dto.UserDto;
import com.lrtech.ecommerce.entities.Role;
import com.lrtech.ecommerce.entities.User;
import com.lrtech.ecommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // List<UserDetailsProjection> result =
    // userRepository.searchUserAndRolesByEmail(username);
    List<UserDetailsDTO> result1 = userRepository.searchUserAndRolesByEmail1(username);

    if (result1.size() == 0) {
      throw new UsernameNotFoundException("user not found");
    }

    User user = new User();
    user.setEmail(result1.get(0).getUserName());
    user.setPassword(result1.get(0).getPassword());
    for (UserDetailsDTO projection : result1) {
      user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
    }

    return user;
  }

  // se tiver um cara authenticado ele pega o context do spring security para
  // achar e como estamos usansdo o jwt podemos usar o getprincipal para fazer o
  // castpara o jwt e usamos os claim para puxar o username e fazer a consulta no
  // banco de dados e puxar as informações dele a partir do email
  protected User authenticated() {

    try {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
      String username = jwtPrincipal.getClaim("username");

      return userRepository.findByEmail(username).get();
    } catch (Exception e) {
      throw new UsernameNotFoundException("usuario nao logado");
    }
    

  }
  @Transactional(readOnly = true)
  public UserDto getMe(){

    User user = authenticated();

    return new UserDto(user);
  }

}
