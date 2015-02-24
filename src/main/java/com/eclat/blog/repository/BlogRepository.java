package com.eclat.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eclat.blog.entity.Blog;
import com.eclat.blog.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
