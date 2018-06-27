package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(path = "peers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userEmail);
		return userRepository.findByOrganisation(user.getOrganisation());
	}

	@RequestMapping(path = "newuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean addUser(@RequestBody User user) {
		userRepository.save(user);
		return true;
	}

	@RequestMapping(path = "edituser/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean addUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
		user.setUserId(userId);
		userRepository.save(user);
		return true;
	}
	@RequestMapping(path = "removeuser/{userId}", method = RequestMethod.DELETE)
	public boolean removeUser(@PathVariable("userId") Integer userId) {
		User user = new User();
		user.setUserId(userId);
		userRepository.delete(userId);
		return true;
	}
}
