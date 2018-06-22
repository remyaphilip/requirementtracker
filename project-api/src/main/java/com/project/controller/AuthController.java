package com.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.repository.JdbcRepository;

@RestController
@RequestMapping("/")
public class AuthController {
	
	@Autowired
	public JdbcRepository jdbcRepository;
	
	@RequestMapping(path = "login",method = RequestMethod.GET)
	public ResponseEntity getAuth(){
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path = "notlogin",method = RequestMethod.GET)
	public void getBlah(){
		System.out.println("Hit the secure method!!");	
	}
	
	@RequestMapping(path = "secured",method = RequestMethod.GET)
	public void getBlah2(HttpSession session){
		System.out.println(session.getId());
		
		System.out.println("Hit the secure method!!");	
	}
	
	
	
	
	

}
