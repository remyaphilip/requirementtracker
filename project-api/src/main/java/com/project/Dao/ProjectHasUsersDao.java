package com.project.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Project;
import com.project.model.UserHasProjectId;
import com.project.repository.JdbcRepository;
import com.project.repository.ProjectRepository;

@Service
public class ProjectHasUsersDao {

	@Autowired
	public JdbcRepository jdbcRepository;

	@Autowired
	ProjectRepository projectRepository;

	public List<Project> getAllProject(List<UserHasProjectId> temp){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(UserHasProjectId item: temp){
			ids.add(item.getProjectId());
		}
	//	ArrayList<Project> p = jdbcRepository.getAllProject(ids);
	//	System.out.println(projectRepository.findAll(ids).toString());
		
		return projectRepository.findAll(ids);
	}

}
