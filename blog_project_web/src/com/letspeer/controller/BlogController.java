package com.letspeer.controller;

import java.io.IOException;
import java.util.HashMap;

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

import com.letspeer.util.BlogUtil;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
//		ApplicationContext applicationContext = ServletUtil.getApplicationContext(this.getServletContext());
//		TilesContainer container = TilesAccess.getContainer(applicationContext);
//		
//		ServletRequest servletRequest = new ServletRequest(applicationContext, request, response);
//		servletRequest.getRequest().setAttribute("MSG", "Za3al W Khadra");
//		container.render("index", servletRequest);
		
		HashMap<String, Object> tt = new HashMap<String, Object>() ; 
		tt.put("MSG", "abdullah");
		
		BlogUtil.RenderPage("index", tt, request, response);
	}

}
