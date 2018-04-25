package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.issuetrack.model.Comment;
import com.issuetrack.model.Issue;
import com.project.repository.JdbcRepository;
@RequestMapping(path = "/")
@RestController
public class IssueController {
	
	@Autowired
	public JdbcRepository jdbcRepository;
	
	
	@RequestMapping(path = "project/{id}/issues",method = RequestMethod.GET)
	public List<Issue> getProjectIssues(@PathVariable("id") Integer id){
		return jdbcRepository.issueFindAll(id);
	}
		
		@RequestMapping(path = "issue/{id}/comment",method = RequestMethod.GET)
		public List<Comment> getIssueComments(@PathVariable("id") Integer id){
			return jdbcRepository.commentFindAll(id);
		}
		
		@RequestMapping(path = "issue/{userId}",method = RequestMethod.GET)
		public List<Issue> getIssuePerUser(@PathVariable("userId") Integer userId){
			return jdbcRepository.issueFindAllPerUser(userId);
		}
		
	}


