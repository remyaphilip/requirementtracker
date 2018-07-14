package com.proman.api.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proman.api.issuetrack.model.Comment;
import com.proman.api.issuetrack.model.Issue;
import com.proman.api.issuetrack.repository.IssueRepository;
import com.proman.api.project.model.Project;
import com.proman.api.project.repository.JdbcRepository;
import com.proman.api.project.repository.ProjectRepository;
import com.proman.api.user.model.User;
import com.proman.api.user.repository.UserRepository;

@RequestMapping(path = "/")
@RestController
public class IssueController {
	@Autowired
	public IssueRepository issueRepository;

	@Autowired
	public JdbcRepository jdbcRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ProjectRepository projectRepository;

	@RequestMapping(path = "project/{id}/issues", method = RequestMethod.GET)
	public List<Issue> getProjectIssues(@PathVariable("id") Integer id) {
		return jdbcRepository.issueFindAll(id);
	}

	@RequestMapping(path = "issue/{id}/comment", method = RequestMethod.GET)
	public List<Comment> getIssueComments(@PathVariable("id") Integer id) {
		return jdbcRepository.commentFindAll(id);
	}

	@RequestMapping(path = "issue", method = RequestMethod.GET)
	public List<Issue> getIssuePerUser() {
		String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userEmail);
		List<Project> projectList = new ArrayList<Project>();
		projectList = projectRepository.findByOrganisation(user.getOrganisation());
		Issue issue = new Issue();
		List<Issue> issues = new ArrayList<Issue>();
			projectList.forEach(p->{
			issue.setProjectId(p.getProjectId());
			Example<Issue> ex1 = Example.of(issue);
			issues.addAll(issueRepository.findAll(ex1));
		});
		return issues;
	}

	@RequestMapping(path = "issue/{projectId}", method = RequestMethod.POST)
	public boolean AddIssue(@PathVariable("projectId") Integer projectId, @RequestBody Issue issue) {
		issue.setProjectId(projectId);
		issueRepository.save(issue);
		return true;
	}

	@RequestMapping(path = "editissue/{issueId}/{projectId}", method = RequestMethod.POST)
	public boolean EditIssue(@PathVariable("issueId") Integer issueId, @PathVariable("projectId") Integer projectId,
			@RequestBody Issue issue) {
		issue.setProjectId(projectId);
		issue.setIssueId(issueId);
		issueRepository.save(issue);
		return true;

	}

	@RequestMapping(path = "delete/{issueId}", method = RequestMethod.DELETE)
	public boolean DeleteIssue(@PathVariable("issueId") Integer issueId) {
		issueRepository.delete(issueId);
		return true;
	}
}
