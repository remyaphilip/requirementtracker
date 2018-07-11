package com.project.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseIntegrationTest {

	@Autowired
	JdbcTemplate template;
	
	
	@Test
	public void testDbConnection() throws Exception{
		Connection con = template.getDataSource().getConnection();

		template.query("SELECT * FROM proman_db.project where project_id=2", new ProjectDataHandler());
	}
	
	
	static class ProjectDataHandler implements RowCallbackHandler{
		@Override
		public void processRow(ResultSet arg0) throws SQLException {
			System.out.println(arg0.getString("name"));
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class ProjectData{
		private int project_id;
		private String name;
		private int board_id;
		private int organisation;
	}
}
