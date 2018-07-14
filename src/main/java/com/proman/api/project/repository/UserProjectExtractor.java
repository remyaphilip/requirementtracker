package com.proman.api.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.proman.api.project.model.UserHasProjectId;

public class UserProjectExtractor implements ResultSetExtractor<List<UserHasProjectId>> {

	@Override
	public List<UserHasProjectId> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<UserHasProjectId> list = new ArrayList<UserHasProjectId>();
		while(rs.next()){
			UserHasProjectId userHasProjectId = new UserHasProjectId();
			userHasProjectId.setProjectId(rs.getInt("project_id"));
			userHasProjectId.setUserId(rs.getInt("user_id"));
			list.add(userHasProjectId);
			
		}
		return list;
	}

}
