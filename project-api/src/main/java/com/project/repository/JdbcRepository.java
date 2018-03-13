package com.project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.issuetrack.model.Comment;
import com.issuetrack.model.Issue;
@Repository
public interface JdbcRepository {

	public List<Issue> issueFindAll(Integer id);
	public List<Comment> commentFindAll(Integer id);

}
