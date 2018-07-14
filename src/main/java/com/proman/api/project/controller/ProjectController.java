package com.proman.api.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proman.api.issuetrack.model.Issue;
import com.proman.api.issuetrack.repository.IssueRepository;
import com.proman.api.project.Dao.ProjectHasUsersDao;
import com.proman.api.project.model.Card;
import com.proman.api.project.model.ListType;
import com.proman.api.project.model.Project;
import com.proman.api.project.model.Role;
import com.proman.api.project.model.UserHasProject;
import com.proman.api.project.model.UserHasProjectId;
import com.proman.api.project.repository.BoardRepository;
import com.proman.api.project.repository.CardRepository;
import com.proman.api.project.repository.JdbcRepository;
import com.proman.api.project.repository.ListRepository;
import com.proman.api.project.repository.ListTypeRepository;
import com.proman.api.project.repository.ProjectRepository;
import com.proman.api.project.repository.RoleRepository;
import com.proman.api.project.repository.UserHasProjectRepository;
import com.proman.api.user.model.User;

@RestController
@RequestMapping(path = "/")
public class ProjectController {

	// private static final boolean ListType = false;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	public ProjectRepository projectRepository;
	@Autowired
	public UserHasProjectRepository userHasProjectRepo;
	@Autowired
	public RoleRepository roleRepo;
	@Autowired
	public JdbcRepository jdbcRepository;
	@Autowired
	public ProjectHasUsersDao projectHasUsersDao;
	@Autowired
	public ListTypeRepository listTypeRepository;
	@Autowired
	public ListRepository listRepository;
	@Autowired
	public CardRepository cardRepository;
	@Autowired
	public IssueRepository issueRepository;

	@RequestMapping(path = "project/{id}/", method = RequestMethod.POST)
	public Project addProject(@PathVariable("id") Integer id, @RequestBody Project project) {
		project.setBoard(boardRepository.findOne(id));
		projectRepository.save(project);
		return project;

	}

	@RequestMapping(path = "project/{id}/", method = RequestMethod.GET)
	public Project getProject(@PathVariable("id") Integer id) {
		return projectRepository.findOne(id);
	}

	@RequestMapping(path = "userproject/role/{roleId}", method = RequestMethod.POST)
	public UserHasProject addUserToProject(@PathVariable("roleId") Integer roleId,
			@RequestBody UserHasProjectId userProjectId) {
		Role r = roleRepo.getOne(roleId);
		UserHasProject userProject = new UserHasProject(userProjectId, r);
		userHasProjectRepo.save(userProject);
		return userProject;
	}

	@RequestMapping(path = "addusertoproject", method = RequestMethod.POST)
	public boolean mapUserToProject(@RequestBody UserHasProjectId userProjectId) {
		Role r = roleRepo.getOne(1);
		UserHasProject userProject = new UserHasProject(userProjectId, r);
		userHasProjectRepo.save(userProject);
		return true;
	}

	@RequestMapping(path = "project/{organisation}", method = RequestMethod.GET)
	public List<Project> getUserProject(@PathVariable("organisation") String organisation) {
		Project project = new Project();
		project.setOrganisation(organisation);
		Example<Project> ex = Example.of(project);
		// projectRepository.findAll(example)
		// List<UserHasProjectId> upi = jdbcRepository.getUserProject(userId);
		return projectRepository.findAll(ex);
	}

	@RequestMapping(path = "projectuser/{projectId}", method = RequestMethod.GET)
	public List<User> getProjectUser(@PathVariable("projectId") Integer projectId) {
		return jdbcRepository.getAllProjectUser(projectId);
	}

	@RequestMapping(path = "newproject", method = RequestMethod.POST)
	public boolean addProject(@RequestBody Project project) {
		project.setBoard(boardRepository.getOne(1));
		ListType listType = new ListType();
		listType.setBoardId(1);
		Example<ListType> ex = Example.of(listType);
		List<ListType> listTypes = new ArrayList<ListType>();
		listTypes = listTypeRepository.findAll(ex);
		projectRepository.saveAndFlush(project);
		listTypes.forEach(lType -> {
			com.proman.api.project.model.List list = new com.proman.api.project.model.List();
			list.setBoard(boardRepository.findOne(1));
			list.setListName(lType.getListname());
			list.setProjectId(project.getProjectId());
			listRepository.save(list);
		});
		return true;

	}

	@RequestMapping(path = "editproject/{projectId}", method = RequestMethod.POST)
	public boolean editProject(@PathVariable("projectId") Integer projectId, @RequestBody Project project) {
		project.setProjectId(projectId);
		project.setBoard(boardRepository.findOne(1));
		projectRepository.save(project);
		return true;
	}

	@RequestMapping(path = "removeproject/{projectId}",method = RequestMethod.DELETE)
		public boolean removeProject(@PathVariable("projectId") Integer projectId){
		  Project project = new Project();
		  project.setProjectId(projectId);
		  
		  com.proman.api.project.model.List list = new com.proman.api.project.model.List();
		  list.setProjectId(projectId);
		  Example<com.proman.api.project.model.List> ex = Example.of(list); 
		  listRepository.delete(listRepository.findAll(ex));
		  
		  Card card = new Card();
		  card.setProjectId(projectId);
		  Example<Card> ex1 = Example.of(card);
		  cardRepository.delete(cardRepository.findAll(ex1));
		  
		  Issue issue = new Issue();
		  issue.setProjectId(projectId);
		  Example<Issue> ex2 = Example.of(issue);
		  issueRepository.delete(issueRepository.findAll(ex2));
		  
		  projectRepository.delete(projectId);
		  
		  return true;
		}

}
