package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.Dao.UserDto;
import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	public List<UserDto> getUserDtoList() {
		String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userEmail);
		List<User> userList = new ArrayList<User>();
		userList = userRepository.findByOrganisation(user.getOrganisation());
		List<UserDto> dto = new ArrayList<UserDto>();
		for (User tempuser : userList) {
			dto.add(createDto(tempuser));
		}

		return dto;

	}

	public UserDto createDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setJobTitle(user.getJobTitle());
		userDto.setUserRole(user.getUserRole());
		return userDto;
	}

}
