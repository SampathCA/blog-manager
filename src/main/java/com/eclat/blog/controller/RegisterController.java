package com.eclat.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eclat.blog.entity.User;
import com.eclat.blog.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	// create form
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	// show register
	@RequestMapping
	public String showRegister() {
		return "user-register";
	}

	// reseive form from user and save to db
	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
}
