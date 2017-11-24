package com.letspeer.blog.dao.impl;
/**
* author alaa abuzaghleh
**/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.letspeer.blog.dao.UserDao;
import com.letspeer.blog.model.User;

public class UserDaoImpl implements UserDao {
	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public UserDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
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
	public Integer addUser(User user) {
		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO users "
					+ "(`first_name`,`last_name`,`email`,`password`,`profile_picture`,`about_me`,`deleted`) VALUES"
					+ "(?,?,?,?,?,?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt,Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, user.getFirstName());
			pStmt.setString(2, user.getLastName());
			pStmt.setString(3, user.getEmail());
			pStmt.setString(4, user.getPassword());
			pStmt.setString(5, user.getProfilePicture());
			pStmt.setString(6, user.getAboutMe());
			if (user.getDeleted()) {
				pStmt.setString(7, "1");
			} else {
				pStmt.setString(7, "0");
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
	public User getUserById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM users WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				User user = new User();
				user.setAboutMe(result.getString("about_me"));
				user.setDeleted(result.getString("deleted").equals('0') ? false : true);
				user.setEmail(result.getString("email"));
				user.setFirstName(result.getString("first_name"));
				user.setId(result.getInt("id"));
				user.setLastName(result.getString("last_name"));
				user.setProfilePicture(result.getString("profile_picture"));
				return user;

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
	public List<User> getUsers(Integer startRow, Integer rowCount) {
		ResultSet result = null;
		List<User> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM users WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, startRow);
			pStmt.setInt(2, rowCount);
			result = pStmt.executeQuery();
			while (result.next()) {
				User user = new User();
				user.setAboutMe(result.getString("about_me"));
				user.setDeleted(result.getString("deleted").equals('0') ? false : true);
				user.setEmail(result.getString("email"));
				user.setFirstName(result.getString("first_name"));
				user.setId(result.getInt("id"));
				user.setLastName(result.getString("last_name"));
				user.setProfilePicture(result.getString("profile_picture"));
				ls.add(user);
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
	public List<User> getUsers() {
		return getUsers(0, 1000);
	}

	@Override
	public Boolean updateUser(User user) {
		try {

			String stmt = "UPDATE users SET "
					+ "first_name=? ,last_name=?,email=? ,password=? ,profile_picture=?,about_me=? ,deleted=? "
					+ "WHERE id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);
			pStmt.setString(1, user.getFirstName());
			pStmt.setString(2, user.getLastName());
			pStmt.setString(3, user.getEmail());
			pStmt.setString(4, user.getPassword());
			pStmt.setString(5, user.getProfilePicture());
			pStmt.setString(6, user.getAboutMe());
			if (user.getDeleted()) {
				pStmt.setString(7, "1");
			} else {
				pStmt.setString(7, "0");
			}
			pStmt.setInt(8, user.getId());
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
	public void deleteUser(Integer id) {
		User u = getUserById(id);
		u.setDeleted(true);
		updateUser(u);

	}

}
