package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

	public List<Project> findByOrganisation(String organisation);
	
}
