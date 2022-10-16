package com.acks.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acks.model.User5;
import com.acks.userrepository.UserRepository;

@Controller
@RequestMapping("/user")
public class userController {
	@Autowired
	private UserRepository userRepository;

	
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		String userName=principal.getName();
		System.out.println("USERNAME"+userName);
	    User5 user = this.userRepository.getUserByName(userName);
	    System.out.println("User5"+user);
	    
	    model.addAttribute("user", user);
	
		
		return "Normal/user_dashboard";
		
	}

}
