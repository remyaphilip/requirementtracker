package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dao.ProjectHasUsersDao;
import com.project.model.Project;
import com.project.model.Role;
import com.project.model.UserHasProject;
import com.project.model.UserHasProjectId;
import com.project.repository.BoardRepository;
import com.project.repository.JdbcRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserHasProjectRepository;
import com.user.model.User;

@RestController
@RequestMapping(path = "/")
public class ProjectController {

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

	@RequestMapping(path = "userproject/{userId}", method = RequestMethod.GET)
	public List<Project> getUserProject(@PathVariable("userId") Integer userId) {
		List<UserHasProjectId> upi = jdbcRepository.getUserProject(userId);
		return projectHasUsersDao.getAllProject(upi);
	}
	
	@RequestMapping(path = "projectuser/{projectId}", method = RequestMethod.GET)
	public List<User> getProjectUser(@PathVariable("projectId") Integer projectId) {
		return jdbcRepository.getAllProjectUser(projectId);
	}

	
}
