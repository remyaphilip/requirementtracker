package com.project.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.issuetrack.model.Comment;
import com.issuetrack.model.Issue;

@Component
public class Mapper implements JdbcRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public Mapper(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Issue> issueFindAll(Integer id) {
		String ISSUE_FETCH_SQL = "select " + "i.issue_id" + ",i.title" + ",i.creation_date" + ",i.description"
				+ ",i.type" + ",i.category" + ",i.due_date" + ",i.reported_by_id" + ",i.assign_to_id" + ",i.estimate"
				+ ",i.time_spent" + ",i.status_code" + ",i.severity_code" + ",i.priority_code" + ",i.project_id "
				+ "from issue_track.issue i,project p " + "where i.project_id = p.project_id " + "and p.project_id = ?";
		return this.jdbcTemplate.query(ISSUE_FETCH_SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement ISSUE_FETCH_SQL) throws SQLException {
				ISSUE_FETCH_SQL.setInt(1, id);
			}

		}, new IssueResultSetExtractor());
	}

	public List<Comment> commentFindAll(Integer id) {
		String COMMENT_FETCH_SQL = "select " + "c.comment_id " + ",c.description " + ",c.log_date "
				+ "from issue_track.issue i,issue_track.comment c where i.issue_id = c.issue_id "
				+ "and i.issue_id = ?";

		return this.jdbcTemplate.query(COMMENT_FETCH_SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement Comment_fetch_sql) throws SQLException {

				Comment_fetch_sql.setInt(1, id);
			}
		}, new CommentResultSetExtractor());

	}

}
