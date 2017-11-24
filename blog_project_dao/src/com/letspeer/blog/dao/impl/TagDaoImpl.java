package com.letspeer.blog.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.letspeer.blog.dao.TagDao;
import com.letspeer.blog.model.Tag;

public class TagDaoImpl implements TagDao {
	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public TagDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbUserPwd = dbUserPwd;
	}

	//test
	
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
	public Integer addTag(Tag tag) {
		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO tags " + "(`tag_name`,`deleted`) VALUES" + "(?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, tag.getTagName());

			if (tag.getDeleted()) {
				pStmt.setString(2, "1");
			} else {
				pStmt.setString(2, "0");
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
	public Tag getTagById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM tags WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				Tag tag = new Tag();
				tag.setTagName("tag_name");
				tag.setId(result.getInt("id"));
				tag.setDeleted(result.getString("deleted").equals('0') ? false : true);

				return tag;

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
	public List<Tag> getTags(Integer start, Integer count) {
		ResultSet result = null;
		List<Tag> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM tags WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, start);
			pStmt.setInt(2, count);
			result = pStmt.executeQuery();
			while (result.next()) {

				Tag tag = new Tag();
				tag.setId(result.getInt("id"));
				tag.setTagName("tag_name");
				tag.setDeleted(result.getString("deleted").equals('0') ? false : true);

				ls.add(tag);
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
	public List<Tag> getTags() {

		return getTags(0,1000);
	}

	@Override
	public Boolean updateTag(Tag tag) {
		try {

			String stmt = "UPDATE tags SET " + "tag_name=? ,deleted=? " + "WHERE id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);
			pStmt.setString(1, tag.getTagName());

			if (tag.getDeleted()) {
				pStmt.setString(2, "1");
			} else {
				pStmt.setString(2, "0");
			}
			pStmt.setInt(3, tag.getId());
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
	public void deleteTag(Integer id) {

		Tag t = getTagById(id);

		t.setDeleted(true);
		updateTag(t);

	}

}
