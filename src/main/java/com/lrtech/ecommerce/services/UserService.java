package com.lrtech.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.ecommerce.dto.RoleDto;
import com.lrtech.ecommerce.dto.UserDetailsDTO;
import com.lrtech.ecommerce.dto.UserDto;
import com.lrtech.ecommerce.dto.UserRegisterDto;
import com.lrtech.ecommerce.entities.Role;
import com.lrtech.ecommerce.entities.User;
import com.lrtech.ecommerce.repositories.RoleRepository;
import com.lrtech.ecommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  @Lazy
  private PasswordEncoder passwordEncoder;

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
  public List<UserDto> getAllUsers(){
    List<User> result = userRepository.findAll();



    return result.stream().map(x-> new UserDto(x)).toList();
  }

  @Transactional
  public UserRegisterDto registerMe(UserRegisterDto dto) {
    User user = new User();
    user.setUserName(dto.getUserName());
    user.setPhone(dto.getPhone());
    user.setEmail(dto.getEmail());
    user.setBirthDate(dto.getBirthDate());
    String passwordcod = passwordEncoder.encode(dto.getPassword());
    user.setPassword(passwordcod);

    
    for (RoleDto roleDto : dto.getRoles()) {
     
     Optional<Role> role = roleRepository.findByAuthority(roleDto.getAuthority());
     System.out.println(role.get()); 
     // Role role = new Role();
      //role.setId(roleDto.getId());
      //role.setAuthority(roleDto.getAuthority());
      user.getRoles().add(role.get());
     
      System.out.println(user.getRoles());

    }

    user = userRepository.save(user);

    return new UserRegisterDto(user);
  }

  @Transactional(readOnly = true)
  public UserDto getMe() {

    User user = authenticated();

    return new UserDto(user);
  }

}
