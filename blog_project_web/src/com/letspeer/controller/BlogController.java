package com.letspeer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;

import com.letspeer.blog.dao.BlogEntryDao;
import com.letspeer.blog.dao.CategoryDao;
import com.letspeer.blog.dao.impl.BlogEntryDaoImpl;
import com.letspeer.blog.dao.impl.CategoryDaoImpl;
import com.letspeer.blog.model.Category;
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
			System.out.println(ls.size() + ">>>>>>>>>>>> " + ls.get(0).getCategoryName());
			HashMap<String, Object> catMap = new HashMap<String, Object>() ; 
			catMap.put("categories", ls) ; 
			BlogUtil.RenderPage("newBlog", catMap, request, response);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
