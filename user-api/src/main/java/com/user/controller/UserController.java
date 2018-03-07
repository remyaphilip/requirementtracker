package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.repository.UserRepository;

@RestController
@RequestMapping(path = "/")
public class UserController {

	@Autowired
	public UserRepository userRepository;

	@RequestMapping(path = "user", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		userRepository.save(user);
		return user;

	}

	@RequestMapping(path = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("id") Integer id) {
		return (userRepository.findOne(id));
	}

	@RequestMapping(path = "user/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers() {
		return (userRepository.findAll());
	}
}
