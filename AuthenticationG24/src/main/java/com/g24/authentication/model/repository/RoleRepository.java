package com.g24.authentication.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g24.authentication.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> 
{

	Role findByName(String name);
}
