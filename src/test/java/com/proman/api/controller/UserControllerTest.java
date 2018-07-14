package com.proman.api.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.proman.api.project.Dao.UserDto;
import com.proman.api.project.controller.UserController;
import com.proman.api.project.service.UserService;
import com.proman.api.user.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController toTest;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Test
	public void test(){
		List<UserDto> list = new ArrayList<>();
		when(userService.getUserDtoList()).thenReturn(list);
		
		List<UserDto> testResult = toTest.getAllUsers();
 		
		assertThat(testResult,is(new ArrayList<>()));
	}
	
	
}
