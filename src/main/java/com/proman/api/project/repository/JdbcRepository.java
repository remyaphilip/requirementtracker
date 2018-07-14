package com.proman.api.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.proman.api.issuetrack.model.Comment;
import com.proman.api.issuetrack.model.Issue;
import com.proman.api.project.model.UserHasProjectId;
import com.proman.api.user.model.User;

@Repository
public interface JdbcRepository {

	public List<Issue> issueFindAll(Integer id);

	public List<Issue> issueFindAllPerUser(Integer userId);

	public List<Comment> commentFindAll(Integer id);

	public User getUser(String email, String passwordhash);

	public List<UserHasProjectId> getUserProject(Integer userId);

	public List<User> getAllProjectUser(Integer projectId);

}

