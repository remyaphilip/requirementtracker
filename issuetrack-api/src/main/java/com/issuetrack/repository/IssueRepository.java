package com.issuetrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.issuetrack.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
	public List<Issue> findByprojectId(List<Integer> projectId);

}
