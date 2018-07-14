package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.project.Dao.UserDto;
import com.project.api.ProjectApplication;
import com.project.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@ContextConfiguration(classes={ProjectApplication.class})

public class TestUserController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	// @Before
	// public void setup() throws Exception {
	// mockMvc = MockMvcBuilders.standaloneSetup(userController)
	// .build();
	//
	//
	// }

	@Test
	public void testGetAllUsers() {
		List<UserDto> mockUserDtos = new ArrayList<UserDto>();
		UserDto u1 = new UserDto();
		u1.setUserId(1);
		u1.setName("Remya");
		u1.setEmail("remya@email.com");
		UserDto u2 = new UserDto();
		u1.setUserId(2);
		u1.setName("testname3");
		u1.setEmail("testname3@email.com");
		mockUserDtos.add(u1);
		mockUserDtos.add(u2);
		Mockito.when(userService.getUserDtoList())
			.thenReturn(mockUserDtos);
		String expected = "[{userId: null, email: remya@email.com," + "jobTitle: null," + "organisation: null,"
				+ "profileImages: null," + "name: Remya," + "userRole: Member  }," + "  { userId: null,"
				+ "email: testname3@email.com," + "jobTitle: null," + "organisation: null," + "profileImages: null,"
				+ "name: testname3," + "userRole: Member" + " }]";

		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/peers")
				.accept(MediaType.APPLICATION_JSON);
			mockMvc.perform(MockMvcRequestBuilders.get("/peers"))
				.andExpect(MockMvcResultMatchers.status()
					.isOk());
			MvcResult result = mockMvc.perform(requestBuilder)
				.andReturn();
			JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
