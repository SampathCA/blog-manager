package com.eclat.blog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eclat.blog.entity.Blog;
import com.eclat.blog.entity.User;
import com.eclat.blog.repository.UserRepository;
import com.eclat.blog.service.BlogService;
import com.eclat.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	// object bound spring controller to jsp
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}

	@RequestMapping("/register")
	public String showRegister() {
		return "user-register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/register.html?success=true";
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principle) {
		String name = principle.getName();
		model.addAttribute("user", userService.findOneWithBlog(name));
		return "user-detail";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddBlog(@ModelAttribute("blog") Blog blog,
			Principal principle) {
		String name = principle.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}

	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog=blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";

	}

	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}
}
