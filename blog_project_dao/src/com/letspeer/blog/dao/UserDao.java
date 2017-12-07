package com.letspeer.blog.dao;

import java.util.List;

import com.letspeer.blog.model.User;

public interface UserDao extends BaseDao {
	
	public Integer addUser(User user);
	public User getUserById(Integer id) ; 
	public List<User> getUsers(Integer startRow, Integer rowCount);
	public List<User> getUsers() ; 
	public Boolean updateUser(User user) ; 
	public void deleteUser(Integer id) ;
	public User getUserByEmail(String email); 
	

}
