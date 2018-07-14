package com.proman.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proman.api.project.model.UserHasProject;
import com.proman.api.project.model.UserHasProjectId;

public interface UserHasProjectRepository extends JpaRepository<UserHasProject, UserHasProjectId> {

}
