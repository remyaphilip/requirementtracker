package com.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.issuetrack.model.Issue;

public class IssueResultSetExtractor implements ResultSetExtractor<List<Issue>> {

	@Override
	public List<Issue> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Issue> map = new HashMap<Integer, Issue>();
		while (rs.next()) {
			Issue issue = new Issue();
			issue.setIssueId(rs.getInt("issue_id"));
			issue.setTitle(rs.getString("title"));
			issue.setCreationDate(rs.getDate("creation_date"));
			issue.setDescription(rs.getString("description"));
			issue.setType(rs.getString("type"));
			issue.setCategory(rs.getString("category"));
			issue.setDueDate(rs.getDate("due_date"));
			issue.setReportedById(rs.getInt("reported_by_id"));
			issue.setAssignToId(rs.getInt("assign_to_id"));
			issue.setEstimate(rs.getInt("estimate"));
			issue.setTimeSpent(rs.getFloat("time_spent"));
			issue.setStatusCode(rs.getString("status_code"));
			issue.setSeverityCode(rs.getInt("severity_code"));
			issue.setPriorityCode(rs.getInt("priority_code"));
			issue.setProjectId(rs.getInt("project_id"));
			map.put(rs.getInt("issue_id"), issue);

		}

		return new ArrayList<Issue>(map.values());
	}

}
