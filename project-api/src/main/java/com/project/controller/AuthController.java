package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.repository.JdbcRepository;
import com.user.model.User;

@RestController
@RequestMapping("/")
public class AuthController {
	
	@Autowired
	public JdbcRepository jdbcRepository;
	
	@RequestMapping(path = "user/{email}/{passwordHash}",method = RequestMethod.GET)
	public User getAuth(@PathVariable("email") String email,@PathVariable("passwordHash") String passwordHash){
		return jdbcRepository.getUser(email, passwordHash);
		
	}
	
	

}
