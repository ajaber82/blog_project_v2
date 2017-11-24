package com.letspeer.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.letspeer.blog.dao.UserDao;
import com.letspeer.blog.dao.impl.UserDaoImpl;
import com.letspeer.blog.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

	
	static int userId ; 
	@Test
	public void test1_AddUser() {
		try {
		User u = new User();
		u.setAboutMe("something");
		u.setDeleted(false);
		u.setEmail("u@xyz.com");
		u.setFirstName("fName");
		u.setLastName("lName");
		u.setPassword("pwd");
		u.setProfilePicture("pic1.jpg");
		
		UserDao dao = new UserDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
				"root" , "12345678");
		
		int res = dao.addUser(u) ; 
		//System.out.println(res);
		userId = res ; 
		assertTrue(res>-1);
		
		}catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@Test
	public void test2_GetUserById() {
		try {
			//System.out.println(userId);
			User u = null ; 
			UserDao dao = new UserDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			u = dao.getUserById(userId) ; 
			assertNotNull(u);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@Test
	public void test3_GetUsers() {
		
		try {
			
			UserDao dao = new UserDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			List<User> ls = dao.getUsers(0, 100) ; 
			assertNotNull(ls);
			assertTrue(ls.size()>=0);
			
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		
	}
	
	@Test
	public void test4_UpdateUsers() {
		
		try {
			
			User u = new User();
			u.setId(userId);
			u.setAboutMe("something");
			u.setDeleted(false);
			u.setEmail("u@xyz.com");
			u.setFirstName("fName");
			u.setLastName("lName");
			u.setPassword("pwd");
			u.setProfilePicture("pic1.jpg");
			
			UserDao dao = new UserDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			
			Boolean result = dao.updateUser(u) ;
			assertFalse(result);
			u = dao.getUserById(userId) ; 
			assertNotNull(u);
			assertTrue(u.getEmail().equals("u@xyz.com"));
		}catch(Exception exp) {
			exp.printStackTrace();
		}	
	}
	
	@Test
	public void test5_deleteUser() {
		UserDao dao = new UserDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
				"root" , "12345678");
		dao.deleteUser(userId);
		
		User u = dao.getUserById(userId) ; 
		assertNull(u);
		
		
	}
	

}
