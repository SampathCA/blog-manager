package com.eclat.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eclat.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
