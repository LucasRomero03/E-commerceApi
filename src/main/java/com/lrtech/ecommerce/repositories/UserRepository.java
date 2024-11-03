package com.lrtech.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lrtech.ecommerce.dto.UserDetailsDTO;
import com.lrtech.ecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  

  @Query(value = " SELECT new com.lrtech.ecommerce.dto.UserDetailsDTO(u.email, u.password, r.id, r.authority) " +
      " FROM User u JOIN  u.roles r" +" WHERE u.email= :email")
  List<UserDetailsDTO> searchUserAndRolesByEmail1(String email);

  // sem usar alias
  //@Query(value = "SELECT new com.devsuperior.demo.dto.UserDetailsDTO(User.email, User.password, Role.id, Role.authority) "
    //  +
      //"FROM User JOIN User.roles Role")
  //List<UserDetailsDTO> searchUserAndRolesByEmail1();

}
