package com.acks.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acks.helper.Message;
import com.acks.model.Contact5;
import com.acks.model.User5;
import com.acks.userrepository.UserRepository;

@Controller
public class TestController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder  passwordEncoder;
	
	//handler for custom login
	@RequestMapping("/signin")
	public String customLogin(Model  model) {
		model.addAttribute("title", "this Is Login Page");
		return "login";
		
	}
	
	
	@RequestMapping("/home")
	public String home(Model model) {
     model.addAttribute("title","This Is Home Page");
		
		return "home";
		
	}

	@GetMapping("/test")
	@ResponseBody
	public String myContact() {
		System.out.println("welcome Spring Boot...");
		User5 user = new User5();
		user.setName("ch");
		user.setEmail("chetan122@yahoo.com");
		user.setPassword("Ch@123");
		user.setRole("Admin");
		user.setEnabled(true);
		user.setImageurl("/image/oo.jpg");
		user.setAbout("Welcome Spring Boot");

		Contact5 contact = new Contact5();
		user.getList().add(contact);
		userRepository.save(user);

		return "test";

	}

	@GetMapping("/login")
	public String htmlHandler(Model model) {

		model.addAttribute("title", "home-This IS About Contact Manager");

		return "home";

	}

	@GetMapping("/about")
	public String aboutHandler(Model model) {

		model.addAttribute("title", "home-This IS About Contact Manager");

		return "test";

	}

	@GetMapping("/signup")
	public String signUp(Model model) {

		model.addAttribute("title", "Register-sart Contact Manager");
		model.addAttribute("user", new User5());
	  
		return "signup";

	}

	// handler for register user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User5 user1,BindingResult result1 ,@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,HttpSession session) {
	
	try {
		
		if(!agreement) {
			System.out.println("you have not agreed terms and conditions");
			throw new Exception("you have not agreed terms and conditions");
		}
		if (result1.hasErrors()) {
			System.out.println("Error"+result1.toString());
			model.addAttribute("user",user1);
			return "signup";
		}
		
		user1.setRole("ROLE_USER");
		user1.setEnabled(true);
		user1.setImageurl("pho.jpg");
		
		user1.setPassword(passwordEncoder.encode(user1.getPassword()));
		
		System.out.println("Agreement" + agreement);
		System.out.println("User5" + user1);
		User5 result = this.userRepository.save(user1);

		
		model.addAttribute("user", new  User5());
		session.setAttribute("message",new Message("Successfully Registered !!","alert-success"));
		return "signup";
	} catch (Exception e) {
		e.getStackTrace();
		model.addAttribute("user", user1);
		session.setAttribute("message",new Message("something went wrong !!"+e.getMessage(),"alert-danger"));
		return "signup";
	}

	}
	
}
