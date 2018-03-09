package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Project;
import com.project.repository.BoardRepository;
import com.project.repository.ProjectRepository;

@RestController
@RequestMapping(path = "/")
public class ProjectController {

	@Autowired
	BoardRepository boardRepository;
	@Autowired
	public ProjectRepository projectRepository;

	@RequestMapping(path = "project/{id}/",method = RequestMethod.POST)
	public Project addProject(@PathVariable("id") Integer id,@RequestBody Project project) {
		project.setBoard(boardRepository.findOne(id));
		projectRepository.save(project);
		return project;
	}
}
