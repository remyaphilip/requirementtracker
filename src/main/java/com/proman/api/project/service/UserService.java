package com.proman.api.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proman.api.project.Dao.UserDto;
import com.proman.api.user.model.User;
import com.proman.api.user.repository.UserRepository;

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

//	@InjectMocks //whatever class you are testing
//	UserService userService; 
//	
//	
//	@Mock // dependencies
//	UserRepository repo;
//	
//	
//	@Test
//	public "test getUserDTOList() returns a list of users"(){
//		given:
//			User user = new User();
//			when(userRepository.findByEmail(_)).thenReturn(user);
//			
//		when:
//			List<UserDto> response = userService.gettUserDtoList()
//		then:
//			response instanceof List
//	}
	
	public UserDto createDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setJobTitle(user.getJobTitle());
		userDto.setUserRole(user.getUserRole());
		return userDto;
	}

}
