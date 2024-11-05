package com.lrtech.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.ecommerce.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
  
  Optional<Role> findByAuthority(String authority);
}
