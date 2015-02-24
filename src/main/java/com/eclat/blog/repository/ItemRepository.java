package com.eclat.blog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eclat.blog.entity.Blog;
import com.eclat.blog.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByBlog(Blog blog, Pageable pageable);
}
