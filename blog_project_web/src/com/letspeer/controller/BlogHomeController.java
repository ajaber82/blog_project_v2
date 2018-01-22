package com.letspeer.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.letspeer.blog.dao.impl.BlogEntryDaoImpl;
import com.letspeer.blog.dao.impl.CategoryDaoImpl;
import com.letspeer.blog.model.BlogEntry;
import com.letspeer.dto.BlogEntryDTO;
import com.letspeer.util.BlogUtil;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class BlogHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogEntryDao blogDao ; 
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		blogDao = new BlogEntryDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogHomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HashMap<String, Object> homeMap = new HashMap<String, Object>() ; 
		
		List<BlogEntry> blogs = this.blogDao.getBlogEntries(0, 20) ; 
		
		
		//List<BlogEntry> blog = new ArrayList <BlogEntry>();
		List<BlogEntryDTO> blogdtos = new ArrayList<BlogEntryDTO>();
		
		for (BlogEntry be : blogs) {
			BlogEntryDTO dto = new BlogEntryDTO();
			dto.setBlogTitle(be.getBlogTitle());
			dto.setBlogSummary(be.getBlogBody().length()>20?be.getBlogBody().substring(0, 20) : 
				be.getBlogBody());
			dto.setCatId(be.getCatId());
			dto.setId(be.getId());
			
			blogdtos.add(dto) ; 
		}
		
		
		
		
		
		homeMap.put("MSG", "Welcome to our blog!");
		homeMap.put("top20Blog" , blogdtos);
		BlogUtil.RenderPage("index", homeMap, request, response);
	}

}
