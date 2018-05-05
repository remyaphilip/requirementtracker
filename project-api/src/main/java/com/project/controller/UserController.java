package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.repository.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	public UserRepository userRepository;

	@RequestMapping(path = "usersperorg/{organisation}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(@PathVariable("organisation") String organisation) {
		User user = new User();
		//organisation = "test";
		user.setOrganisation(organisation);
		Example<User> ex = Example.of(user);
		//System.out.println("test   ");
		System.out.println(userRepository.findAll(ex));
		return userRepository.findAll(ex);
	}

}
