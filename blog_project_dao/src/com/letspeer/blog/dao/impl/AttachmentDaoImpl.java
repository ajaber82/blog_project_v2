package com.letspeer.blog.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.letspeer.blog.dao.AttachmentDao;
import com.letspeer.blog.model.Attachment;
import com.letspeer.blog.model.Comment;

public class AttachmentDaoImpl  implements AttachmentDao{
	
	
	
	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public AttachmentDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
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
	public Integer addAttachment(Attachment attachment) {
		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO attachment "
					+ "(`file_bath`,`blog_id`,`deleted`) VALUES"
					+ "(?,?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt,Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, attachment.getFileBath());
			pStmt.setInt(2, attachment.getBlogId());
	
			if (attachment.getDeleted()) {
				pStmt.setString(3, "1");
			} else {
				pStmt.setString(3, "0"); 
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
	public Attachment getAttachmentById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM attachment WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				Attachment attachment = new Attachment();
				attachment.setFileBath(result.getString("file_bath"));
				attachment.setId(result.getInt("id"));
				attachment.setBlogId(result.getInt("blog_id"));
				attachment.setDeleted(result.getString("deleted").equals('0') ? false : true);

				return attachment;

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
	public List<Attachment> getAttachments(Integer start, Integer count) {
		ResultSet result = null;
		List<Attachment> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM attachment WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, start);
			pStmt.setInt(2, count);
			result = pStmt.executeQuery();
			while (result.next()) {
				Attachment attachment = new Attachment();
				attachment.setFileBath(result.getString("file_bath"));
				attachment.setId(result.getInt("id"));
				attachment.setBlogId(result.getInt("blog_id"));
				attachment.setDeleted(result.getString("deleted").equals('0') ? false : true);
				ls.add(attachment);
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
	public List<Attachment> getAttachments() {
		
		return getAttachments(0,1000);
	}

	@Override
	public Boolean updateAttachment(Attachment attachment) {
		try {

			String stmt = "UPDATE attachment SET "
					+ "file_bath=? ,blog_id=? ,deleted=? "
					+ "WHERE id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);
			pStmt.setString(1, attachment.getFileBath());
			pStmt.setInt(2, attachment.getBlogId());
			
			if (attachment.getDeleted()) {
				pStmt.setString(3, "1");
			} else {
				pStmt.setString(3, "0");
			}
			pStmt.setInt(4, attachment.getId());
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
	public void deleteAttachment(Integer id) {
		Attachment a = getAttachmentById(id);
		a.setDeleted(true);
		updateAttachment(a);
		
	}

	

}
