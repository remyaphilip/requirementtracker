package com.issuetrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.issuetrack.model.Issue;
import com.issuetrack.repository.IssueRepository;

@RestController
@RequestMapping(path = "/")
public class IssuetrackController {

	@Autowired
	public IssueRepository issueRepository;
	
//	@Autowired
//	public ProjectRepository projectRepository;
	
	@RequestMapping(path = "issue",method = RequestMethod.POST)
	public Issue createIssue(@RequestBody Issue issue){
//       issue.setProject(projectRepository.getOne(id));
       issueRepository.save(issue);
       return issue;
		
	}
	
//	@RequestMapping(path = "project/{id}/issue",method = RequestMethod.GET)
//	public Set<Issue> getIssue(@PathVariable("id") Integer id){
//		projectRepository.findOne(id).se
//		return issueRepository
//		
//	}
}
