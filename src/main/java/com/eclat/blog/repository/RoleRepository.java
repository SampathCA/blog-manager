package com.eclat.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eclat.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
