package com.issuetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.issuetrack.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

}
