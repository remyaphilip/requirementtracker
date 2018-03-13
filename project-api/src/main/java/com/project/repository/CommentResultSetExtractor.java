package com.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.issuetrack.model.Comment;

public class CommentResultSetExtractor implements ResultSetExtractor<List<Comment>> {

	@Override
	public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer,Comment> map = new HashMap<Integer,Comment>();
		while(rs.next()){
			 Comment comment = new Comment();
			 comment.setCommentId(rs.getInt("comment_id"));
			 comment.setDescription(rs.getString("description"));
			 comment.setLogDate(rs.getDate("log_Date"));
			 map.put(rs.getInt("comment_id"), comment);
		}
		return new ArrayList<Comment>(map.values());
	}

}
