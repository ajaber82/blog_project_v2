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

import com.letspeer.blog.dao.BlogEntryDao;
import com.letspeer.blog.dao.impl.BlogEntryDaoImpl;
import com.letspeer.blog.model.BlogEntry;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class BlogEntryDaoTest {
	
	static int blogEntryId ; 
	@Test
	public void test1_AddBlogEntry() {
		try {
		BlogEntry be = new BlogEntry();
		be.setBlogTitle(null);
		be.setDeleted(false);
		be.setCatId(2);
		be.setUserId(2);
		be.setBlogBody("lName");
		be.setCreatedTime(new Date().getTime());
		
		
	BlogEntryDao dao = new BlogEntryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
				"root" , "12345678");
		
		int res = dao.addBlogEntry(be) ; 
		//System.out.println(res);
		blogEntryId = res ; 
		assertTrue(res>-1);
		
		}catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@Test
	public void test2_GetBlogEntryById() {
		try {
			//System.out.println(userId);
			BlogEntry be = null ; 
			BlogEntryDao dao = new BlogEntryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			be = dao.getBlogEntryById(blogEntryId) ; 
			assertNotNull(be);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	
	@Test
	public void test3_GetBlogEntry() {
		
		try {
			
			BlogEntryDao dao = new BlogEntryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			List<BlogEntry> ls = dao.getBlogEntries(0, 100) ; 
			assertNotNull(ls);
			assertTrue(ls.size()>=0);
			
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		
	}
	
	@Test
	public void test4_UpdateBlogEntries() {
		
		try {
			
			BlogEntry be = new BlogEntry();
			be.setId(blogEntryId);
			be.setBlogTitle("something");
			be.setDeleted(false);
			be.setBlogBody("ufvvdsv");
			be.setCatId(1);
			be.setUserId(1);
			be.setCreatedTime(new Date().getTime());
			
			
			BlogEntryDao dao = new BlogEntryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
					"root" , "12345678");
			
			Boolean result = dao.updateBlogEntry(be) ;
			assertFalse(result);
			be = dao.getBlogEntryById(blogEntryId) ; 
			assertNotNull(be);
		
		}catch(Exception exp) {
			exp.printStackTrace();
		}	
	}
	
	@Test
	public void test5_deleteBlogEntry() {
		BlogEntryDao dao = new BlogEntryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false" , 
				"root" , "12345678");
		dao.deleteBlogEntry(blogEntryId);
		
		BlogEntry be = dao.getBlogEntryById(blogEntryId) ; 
		assertNull(be);
		
		
	}
	



}
