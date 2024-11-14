package com.souleimen.users.restControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.souleimen.users.entities.User;
import com.souleimen.users.repos.UserRepository;
import com.souleimen.users.service.UserService;
import com.souleimen.users.service.register.RegistrationRequest;
@RestController
@CrossOrigin(origins = "*")

public class UserRestController {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path = "all",method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRep.findAll();
	 }
	
	@PostMapping("/register")
	public User register(@RequestBody RegistrationRequest request) {
		return userService.registerUser(request);
	}
	
	
	@GetMapping("/verifyEmail/{token}") 
	public User verifyEmail(@PathVariable("token") String token){    
		return userService.validateToken(token); 
	}
}
