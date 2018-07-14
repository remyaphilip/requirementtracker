package com.proman.api.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.proman.api.project.Dao.UserDto;
import com.proman.api.project.controller.UserController;
import com.proman.api.project.service.UserService;
import com.proman.api.user.repository.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerMvcTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void test() throws Exception{
		List<UserDto> list = new ArrayList<>();
		when(service.getUserDtoList()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/peers").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		result.getResponse().getContentAsString().equals("[]");
	}
	
}
