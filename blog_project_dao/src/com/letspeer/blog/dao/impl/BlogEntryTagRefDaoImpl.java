package com.letspeer.blog.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.letspeer.blog.dao.BlogEntryTagRefDao;
import com.letspeer.blog.model.BlogEntryTagRef;

public class BlogEntryTagRefDaoImpl implements BlogEntryTagRefDao {

	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public BlogEntryTagRefDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
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
	public Integer addBlogEntryTagRef(BlogEntryTagRef blogEntryTagRef) {
		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO blog_entries_tag_ref " + "(`blog_id`,`tag_id`,`deleted`) VALUES" + "(?,?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, blogEntryTagRef.getBlogId());
			pStmt.setInt(2, blogEntryTagRef.getTagId());

			if (blogEntryTagRef.getDeleted()) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disconnectDb();
		}

		return -1;

	}

	@Override
	public BlogEntryTagRef getBlogEntryTagRefById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM blog_entries_tag_ref WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				BlogEntryTagRef blogEntryTagRef = new BlogEntryTagRef();

				blogEntryTagRef.setDeleted(result.getString("deleted").equals('0') ? false : true);
				blogEntryTagRef.setBlogId(result.getInt("blog_id"));
				blogEntryTagRef.setTagId(result.getInt("tag_id"));

				return blogEntryTagRef;

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
	public List<BlogEntryTagRef> getBlogEntriesTagRef(Integer start, Integer count) {
		ResultSet result = null;
		List<BlogEntryTagRef> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM blog_entries_tag_ref WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, start);
			pStmt.setInt(2, count);
			result = pStmt.executeQuery();
			while (result.next()) {
				BlogEntryTagRef blogEntryTagRef = new BlogEntryTagRef();
				blogEntryTagRef.setDeleted(result.getString("deleted").equals('0') ? false : true);
				blogEntryTagRef.setBlogId(result.getInt("blog_id"));
				blogEntryTagRef.setTagId(result.getInt("tag_id"));
				ls.add(blogEntryTagRef);
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
	public List<BlogEntryTagRef> getBlogEntriesTagRef() {

		return getBlogEntriesTagRef(0, 1000);
	}

	@Override
	public Boolean updateBlogEntryTagRef(BlogEntryTagRef blogEntryTagRef) {
		try {

			String stmt = "UPDATE blog_entries_tag_ref SET " + "blog_id=? ,tag_id=?,cat_id=?,deleted=? "
					+ "WHERE blog_id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);

			pStmt.setInt(1, blogEntryTagRef.getBlogId());
			pStmt.setInt(2, blogEntryTagRef.getTagId());

			if (blogEntryTagRef.getDeleted()) {
				pStmt.setString(3, "1");
			} else {
				pStmt.setString(3, "0");
			}

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
	public void deleteBlogEntryTagRef(Integer id) {
		BlogEntryTagRef betr = getBlogEntryTagRefById(id);
		betr.setDeleted(true);
		updateBlogEntryTagRef(betr);

	}

}
