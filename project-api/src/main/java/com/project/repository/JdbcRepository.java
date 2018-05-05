package com.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.issuetrack.model.Comment;
import com.issuetrack.model.Issue;
import com.project.model.UserHasProjectId;
import com.user.model.User;

@Repository
public interface JdbcRepository {

	public List<Issue> issueFindAll(Integer id);

	public List<Issue> issueFindAllPerUser(Integer userId);

	public List<Comment> commentFindAll(Integer id);

	public User getUser(String email, String passwordhash);

	public List<UserHasProjectId> getUserProject(Integer userId);

	public List<User> getAllProjectUser(Integer projectId);

}

