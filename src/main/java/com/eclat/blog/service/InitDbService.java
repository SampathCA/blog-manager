package com.eclat.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eclat.blog.entity.Blog;
import com.eclat.blog.entity.Item;
import com.eclat.blog.entity.Role;
import com.eclat.blog.entity.User;
import com.eclat.blog.repository.BlogRepository;
import com.eclat.blog.repository.ItemRepository;
import com.eclat.blog.repository.RoleRepository;
import com.eclat.blog.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	@Autowired
	private RoleRepository roleRepository = null;

	@Autowired
	private BlogRepository blogRepository = null;

	@Autowired
	private ItemRepository itemRepository = null;

	@Autowired
	private UserRepository userRepository = null;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleUser.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Blog blogEclat = new Blog();
		blogEclat.setName("eclat");
		blogEclat
				.setUrl("http://hyphenz.blogspot.com/feeds/posts/default?alt=rss");
		blogEclat.setUser(userAdmin);
		blogRepository.save(blogEclat);

		Item item1 = new Item();
		item1.setBlog(blogEclat);
		item1.setTitle("first");
		item1.setLink("http://hyphenz.blogspot.com");
		item1.setPublishDate(new Date());
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setBlog(blogEclat);
		item2.setTitle("second");
		item2.setLink("http://hyphenz.blogspot.com");
		item2.setPublishDate(new Date());
		itemRepository.save(item2);
	}
}
