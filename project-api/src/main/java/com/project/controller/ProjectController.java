package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dao.ProjectHasUsersDao;
import com.project.model.ListType;
import com.project.model.Project;
import com.project.model.Role;
import com.project.model.UserHasProject;
import com.project.model.UserHasProjectId;
import com.project.repository.BoardRepository;
import com.project.repository.JdbcRepository;
import com.project.repository.ListRepository;
import com.project.repository.ListTypeRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserHasProjectRepository;
import com.user.model.User;

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
	public boolean addUserToProject(@RequestBody Project project) {
		project.setBoard(boardRepository.getOne(1));
		ListType listType = new ListType();
		listType.setBoardId(1);
		Example<ListType> ex = Example.of(listType);
		List<ListType> listTypes = new ArrayList<ListType>();
		listTypes = listTypeRepository.findAll(ex);
		projectRepository.saveAndFlush(project);
		listTypes.forEach(lType -> {
			com.project.model.List list = new com.project.model.List();
			list.setBoard(boardRepository.findOne(1));
			list.setListName(lType.getListname());
			list.setProjectId(project.getProjectId());
			listRepository.save(list);
		});
		return true;

	}

}
