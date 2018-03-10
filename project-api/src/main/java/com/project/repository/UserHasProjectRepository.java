package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.UserHasProject;
import com.project.model.UserHasProjectId;

public interface UserHasProjectRepository extends JpaRepository<UserHasProject, UserHasProjectId> {

}
