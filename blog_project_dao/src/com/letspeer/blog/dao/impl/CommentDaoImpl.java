package com.letspeer.blog.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.letspeer.blog.dao.CommentDao;
import com.letspeer.blog.model.Comment;


public class CommentDaoImpl implements CommentDao{
	
	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public CommentDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbUserPwd = dbUserPwd;
	}

	@Override
	public void connectDb() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(dbUrl, dbUser, dbUserPwd);
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		
	}

	@Override
	public void disconnectDb() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer addComment(Comment comment) {
		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO comments "
					+ "(`comment_body`,`created_time`,`user_id`,`blog_id`,`deleted`) VALUES"
					+ "(?,?,?,?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt,Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, comment.getCommentBody());
			if(comment.getCreatedTime()!=null) {
				pStmt.setLong(2, comment.getCreatedTime());
			}else {
				pStmt.setNull(2, Types.BIGINT);
			}
			pStmt.setInt(3, comment.getUserId());
			pStmt.setInt(4, comment.getBlogId());
	
			if (comment.getDeleted()) {
				pStmt.setString(5, "1");
			} else {
				pStmt.setString(5, "0");
			}
			pStmt.execute();
			int autoIncKeyFromApi = -1;

			rs = pStmt.getGeneratedKeys();

			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
				return autoIncKeyFromApi;
			} else {
				return -1;
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			disconnectDb();
		}

		return -1;
	}

	@Override
	public Comment getCommentById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM comments WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				Comment comment = new Comment();
				comment.setCommentBody(result.getString("comment_body"));
				comment.setCreatedTime(result.getLong("created_time"));
				comment.setId(result.getInt("id"));
				comment.setBlogId(result.getInt("blog_id"));
				comment.setUserId(result.getInt("user_id"));
				comment.setDeleted(result.getString("deleted").equals('0') ? false : true);

				return comment;

			} else {
				return null;
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return null;
	}

	@Override
	public List<Comment> getComments(Integer start, Integer count) {
		ResultSet result = null;
		List<Comment> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM comments WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, start);
			pStmt.setInt(2, count);
			result = pStmt.executeQuery();
			while (result.next()) {
				Comment comment = new Comment();
				comment.setCommentBody(result.getString("comment_body"));
				comment.setCreatedTime(result.getLong("created_time"));
				comment.setId(result.getInt("id"));
				comment.setBlogId(result.getInt("blog_id"));
				comment.setUserId(result.getInt("user_id"));
				comment.setDeleted(result.getString("deleted").equals('0') ? false : true);
				ls.add(comment);
			}

			return ls;

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return null;

	}

	@Override
	public List<Comment> getComments() {
		
		return getComments(0,1000);
	}

	@Override
	public Boolean updateComment(Comment comment) {
		try {

			String stmt = "UPDATE comments SET "
					+ "comment_body=? ,created_time=?,user_id=? ,blog_id=? ,deleted=? "
					+ "WHERE id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);
			pStmt.setString(1, comment.getCommentBody());
			if(comment.getCreatedTime()!=null) {
				pStmt.setLong(2, comment.getCreatedTime());
			}else {
				pStmt.setNull(2, Types.BIGINT);
			}
			pStmt.setInt(3, comment.getBlogId());
			pStmt.setInt(4, comment.getUserId());
			if (comment.getDeleted()) {
				pStmt.setString(5, "1");
			} else {
				pStmt.setString(5, "0");
			}
			pStmt.setInt(6, comment.getId());
			Boolean result = pStmt.execute();
			return result;
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			disconnectDb();
		}

		return false;
	}

	@Override
	public void deleteComment(Integer id) {
		Comment c = getCommentById(id);
		c.setDeleted(true);
		updateComment(c);

		
	}
	
	
	

}
