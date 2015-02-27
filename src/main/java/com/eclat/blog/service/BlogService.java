package com.eclat.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclat.blog.entity.Blog;
import com.eclat.blog.entity.User;
import com.eclat.blog.repository.BlogRepository;
import com.eclat.blog.repository.UserRepository;

@Service
public class BlogService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
	}
}
