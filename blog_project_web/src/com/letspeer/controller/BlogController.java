package com.letspeer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.tools.config.Data;

import com.letspeer.blog.dao.BlogEntryDao;
import com.letspeer.blog.dao.CategoryDao;
import com.letspeer.blog.dao.impl.BlogEntryDaoImpl;
import com.letspeer.blog.dao.impl.CategoryDaoImpl;
import com.letspeer.blog.model.BlogEntry;
import com.letspeer.blog.model.BlogEntryDetails;
import com.letspeer.blog.model.Category;
import com.letspeer.blog.model.User;
import com.letspeer.config.BlogConstants;
import com.letspeer.util.BlogUtil;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/blog/*")
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogEntryDao blogDao ; 
	private CategoryDao categoryDao ; 
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		blogDao = new BlogEntryDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);
		categoryDao = new CategoryDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		String path = request.getRequestURI() ; 
		
		if(path.endsWith("/blog/new-blog") || path.endsWith("/blog/new-blog/")) {
			List<Category> ls = this.categoryDao.getCategories() ; 
			HashMap<String, Object> catMap = new HashMap<String, Object>() ; 
			catMap.put("categories", ls) ; 
			BlogUtil.RenderPage("newBlog", catMap, request, response);
		}
		
		
		if(path.contains("/detail/"))
		{
			String[] strArr = path.split("/");
		    Integer id=Integer.parseInt(strArr[BlogConstants.BLOG_ID_PATH_LOCATION]);
 			BlogEntryDetails bed = this.blogDao.getBlogEntryDetailes(id);
 			HashMap<String, Object> detailMap = new HashMap<String, Object>() ; 
 			detailMap.put("blogDetail", bed) ;
 			BlogUtil.RenderPage("blogDetail", detailMap, request, response);
			
		}
		if(path.contains("/categories/"))
		{
			String[] strArr = path.split("/");
		    Integer catId=Integer.parseInt(strArr[BlogConstants.BLOG_ID_PATH_LOCATION]);
		    String  catName= strArr[BlogConstants.BLOG_CATEGORY_lOCATION];
 			List<BlogEntryDetails> ls = this.blogDao.getBlogByCategory(catId);
 			List<Category> categories = this.categoryDao.getCategories() ; 
 			HashMap<String, Object> detailMap = new HashMap<String, Object>() ; 
 			detailMap.put("blogs", ls) ;
 			detailMap.put("categories", categories);
 			detailMap.put("categoryName", catName);
 			BlogUtil.RenderPage("blogCategory", detailMap, request, response);
			
		}
		
		
	}
	
	//@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String path = request.getRequestURI() ; 
		
		if(path.endsWith("/blog/post") || path.endsWith("/blog/post/")) {
			String blogTitle = request.getParameter("txtBlogTitle") ; 
			String blogBody = request.getParameter("txtBlogBody") ; 
			String strCategory = request.getParameter("lsCategory");
			
			List<String> errorList = new ArrayList<String>() ; 
			
			if(blogTitle == null || blogTitle.trim().equals("")) {
				errorList.add("Please enter blog title") ; 
			}
			
			if(blogBody==null || blogBody.trim().equals("")) {
				errorList.add("Please enter blog body") ; 
			}
			
			if(strCategory == null || strCategory.equals("-1")) {
				errorList.add("Please select blog category") ; 
			}
			
			if(errorList.size()>0) {
				List<Category> ls = this.categoryDao.getCategories() ; 
				HashMap<String, Object> objMap = new HashMap<String, Object>() ; 
				objMap.put("categories", ls) ; 
				objMap.put("errorList" , errorList) ; 
				BlogUtil.RenderPage("newBlog", objMap, request, response);
			}else {
				BlogEntry be = new BlogEntry() ; 
				be.setBlogBody(blogBody); 
				be.setBlogTitle(blogTitle); 
				be.setCatId(Integer.parseInt(strCategory));
				be.setCreatedTime(new Date().getTime());
				be.setDeleted(false);
				User u = (User)request.getSession().getAttribute(BlogConstants.USER_SESSION_NAME) ; 
				be.setUserId(u.getId());
				
				int result = this.blogDao.addBlogEntry(be) ; 
				
				if(result>-1) {
					List<Category> ls = this.categoryDao.getCategories() ; 
					HashMap<String, Object> objMap = new HashMap<String, Object>() ; 
					objMap.put("categories", ls) ; 
					objMap.put("message", "Your blog added successfully") ; 
					BlogUtil.RenderPage("newBlog", objMap, request, response);
				}else {
					errorList.add("We are not able to handle your requrst currently. Please try again later");
					List<Category> ls = this.categoryDao.getCategories() ; 
					HashMap<String, Object> objMap = new HashMap<String, Object>() ; 
					objMap.put("categories", ls) ; 
					objMap.put("errorList" , errorList) ; 
					BlogUtil.RenderPage("newBlog", objMap, request, response);
				}
				
			}
			
			
		}
		
		
	}

}
