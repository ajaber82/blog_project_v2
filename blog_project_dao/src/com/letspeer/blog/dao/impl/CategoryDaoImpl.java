package com.letspeer.blog.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.letspeer.blog.dao.CategoryDao;
import com.letspeer.blog.model.Category;

public class CategoryDaoImpl implements CategoryDao {
	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public CategoryDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
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
	public Integer addCategory(Category category) {

		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO categories " + "(`category_name`,`deleted`) VALUES" + "(?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, category.getCategoryName());

			if (category.getDeleted()) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disconnectDb();
		}

		return -1;
	}

	@Override
	public Category getCategoryById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM categories WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				Category category = new Category();
				category.setCategoryName("category_name");
				category.setId(result.getInt("id"));
				category.setDeleted(result.getString("deleted").equals('0') ? false : true);

				return category;

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
	public List<Category> getCategories(Integer start, Integer count) {
		ResultSet result = null;
		List<Category> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM categories WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, start);
			pStmt.setInt(2, count);
			result = pStmt.executeQuery();
			while (result.next()) {

				Category category = new Category();
				category.setId(result.getInt("id"));
				category.setCategoryName(result.getString("category_name"));
				category.setDeleted(result.getString("deleted").equals('0') ? false : true);

				ls.add(category);
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
	public List<Category> getCategories() {

		return getCategories(0,1000);
	}

	@Override
	public Boolean updateCategory(Category category) {
		try {

			String stmt = "UPDATE categories SET " + "category_name=? ,deleted=? " + "WHERE id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);
			pStmt.setString(1, category.getCategoryName());

			if (category.getDeleted()) {
				pStmt.setString(2, "1");
			} else {
				pStmt.setString(2, "0");
			}
			pStmt.setInt(3, category.getId());
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
	public void deleteCategory(Integer id) {

		Category c = getCategoryById(id);
		// System.out.println(c.getCategoryName());

		c.setDeleted(true);
		updateCategory(c);

	}

}
