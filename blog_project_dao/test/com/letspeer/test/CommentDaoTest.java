package com.letspeer.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.letspeer.blog.dao.CommentDao;

import com.letspeer.blog.dao.impl.CommentDaoImpl;

import com.letspeer.blog.model.Comment;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CommentDaoTest {
	
	static int commentId;

	@Test
	public void test1_AddComment() {
		try {

			Comment comment = new Comment();
			
			comment.setCommentBody("This is my first project");
			comment.setCreatedTime(new Date().getTime());
			comment.setUserId(1);
			comment.setBlogId(1);
			comment.setDeleted(false);

			CommentDao dao = new CommentDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root",
					"12345678");

			int res = dao.addComment(comment);
			commentId = res;
			assertTrue(res > -1);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@Test
	public void test2_GetCommentById() {
		try {
			
			Comment c = null ; 
			CommentDao dao = new CommentDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			c = dao.getCommentById(commentId) ; 
			assertNotNull(c);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@Test
	public void test3_GetComments() {
		
		try {
			
			CommentDao dao = new CommentDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			List<Comment> ls = dao.getComments(0, 100) ; 
			assertNotNull(ls);
			assertTrue(ls.size()>=0);
			
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		
	}
	
	@Test
	public void test4_UpdateComments() {
		
		try {
			
			Comment c = new Comment();
			c.setId(commentId);
			c.setCommentBody("something");
			c.setDeleted(false);
			c.setCreatedTime(new Date().getTime());
			c.setUserId(2);
			c.setBlogId(2);
			
			
			CommentDao dao = new CommentDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			
			Boolean result = dao.updateComment(c) ;
			assertFalse(result);
			c = dao.getCommentById(commentId) ; 
			assertNotNull(c);
			//assertTrue(u.getEmail().equals("u@xyz.com"));
		}catch(Exception exp) {
			exp.printStackTrace();
		}	
	}
	
	@Test
	public void test5_deleteComment() {
		CommentDao dao = new CommentDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
				"root" , "12345678");
		dao.deleteComment(commentId);
		
		Comment c = dao.getCommentById(commentId) ; 
		assertNull(c);
		
		
	}
	

	
	


}
