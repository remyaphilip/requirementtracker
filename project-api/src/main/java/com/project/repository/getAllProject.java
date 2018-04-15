package com.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.project.model.Project;

public class getAllProject implements ResultSetExtractor<List<Project>> {

	@Override
	public List<Project> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer,Project> map = new HashMap<Integer,Project>();
		while(rs.next()){
			Project project = new Project();
			project.setProjectId(rs.getInt("project_id"));
			project.setName(rs.getString("name"));
			//project.setBoard(rs.getInt("board_id"));
		}
		return null;
	}

}
