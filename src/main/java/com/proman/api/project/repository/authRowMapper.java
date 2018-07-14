package com.proman.api.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.proman.api.user.model.User;

public class authRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
		User user = new User();

		user.setUserId(rs.getInt("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setPasswordHash(rs.getString("password_hash"));
		user.setEmail(rs.getString("email"));
		user.setJobTitle(rs.getString("job_title"));
		user.setOrganisation(rs.getString("organisation"));
		user.setProfileImages(rs.getString("profile_images"));
		return user;
	}

}
