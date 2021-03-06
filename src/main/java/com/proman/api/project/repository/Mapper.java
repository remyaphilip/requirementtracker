package com.proman.api.project.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.proman.api.issuetrack.model.Comment;
import com.proman.api.issuetrack.model.Issue;
import com.proman.api.project.model.UserHasProjectId;
import com.proman.api.user.model.User;

@Component
public class Mapper implements JdbcRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public Mapper(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Issue> issueFindAll(Integer id) {
		String ISSUE_FETCH_SQL = "select " + "i.issue_id" + ",i.summary" + ",i.creation_date" + ",i.description"
				+ ",i.type" + ",i.category" + ",i.due_date" + ",i.reported_by_id" + ",i.assign_to_id" + ",i.estimate"
				+ ",i.time_spent" + ",i.status_code" + ",i.severity_code" + ",i.priority_code" + ",i.project_id "
				+ ",i.start_date , i.end_date "
//				+ "from issue_track.issue i,project p " + "where i.project_id = p.project_id " + "and p.project_id = ?";
		+ "from issue i,project p " + "where i.project_id = p.project_id " + "and p.project_id = ?";
		return this.jdbcTemplate.query(ISSUE_FETCH_SQL, new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement ISSUE_FETCH_SQL) throws SQLException {
				ISSUE_FETCH_SQL.setInt(1, id);
			}

		}, new IssueResultSetExtractor());
	}

	public List<Comment> commentFindAll(Integer id) {
		String COMMENT_FETCH_SQL = "select " + "c.comment_id " + ",c.description " + ",c.log_date "
//				+ "from issue_track.issue i,issue_track.comment c where i.issue_id = c.issue_id "
				+ "from issue i,comment c where i.issue_id = c.issue_id "
				+ "and i.issue_id = ?";

		return this.jdbcTemplate.query(COMMENT_FETCH_SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement Comment_fetch_sql) throws SQLException {

				Comment_fetch_sql.setInt(1, id);
			}
		}, new CommentResultSetExtractor());

	}

	@Override
	public User getUser(String email, String passwordhash) {
		String auth_fetch_sql = "select u.user_id,u.user_name,u.password_hash,u.email,u.job_title,u.organisation,u.profile_images "
//				+ "from user_service.user u where u.email = ? and u.password_hash = ?";
		+ "from user u where u.email = ? and u.password_hash = ?";
		return this.jdbcTemplate.queryForObject(auth_fetch_sql, new Object[] { email, passwordhash },
				new authRowMapper());
	}

	@Override
	public List<UserHasProjectId> getUserProject(Integer userId) {
//		String user_project_sql = "select u.project_id,u.user_id from project_mgmt.user_has_project u where u.user_id = ?";
		String user_project_sql = "select u.project_id,u.user_id from user_has_project u where u.user_id = ?";
		return this.jdbcTemplate.query(user_project_sql, new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement user_project_sql) throws SQLException {
				user_project_sql.setInt(1, userId);

			}

		}, new UserProjectExtractor());
	}

	@Override
	public List<Issue> issueFindAllPerUser(Integer userId) {
		String user_issue_all_sql = "select i.issue_id" + ",i.summary" + ",i.creation_date" + ",i.description" + ",i.type"
				+ ",i.category" + ",i.due_date" + ",i.reported_by_id" + ",i.assign_to_id" + ",i.estimate"
				+ ",i.time_spent" + ",i.status_code" + ",i.severity_code" + ",i.priority_code" + ",i.project_id"
				+ ",i.start_date , i.end_date "
//				+ " from issue_track.issue i," + "project_mgmt.user_has_project u" + " where u.user_id = ?"
				+ " from issue i," + "user_has_project u" + " where u.user_id = ?"
				+ " and i.project_id = u.project_id";
		return this.jdbcTemplate.query(user_issue_all_sql, new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement user_issue_all_sql) throws SQLException {
				user_issue_all_sql.setInt(1, userId);

			}

		}, new IssueResultSetExtractor());

	}

	@Override
	public List<User> getAllProjectUser(Integer projectId) {
		// TODO Auto-generated method stub
//		String user_project_sql = "select u.user_id,u.user_name from project_mgmt.user_has_project a,user_service.user u"
				String user_project_sql = "select u.user_id,u.user_name from user_has_project a,user u"
				+ " where a.project_id = ? " + "and u.user_id = a.user_id";
		return this.jdbcTemplate.query(user_project_sql, new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement user_project_sql) throws SQLException {
				user_project_sql.setInt(1, projectId);

			}

		}, new ProjectUserExtractor());

	}

}
