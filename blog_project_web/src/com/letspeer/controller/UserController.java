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

import com.letspeer.blog.dao.UserDao;
import com.letspeer.blog.dao.impl.UserDaoImpl;
import com.letspeer.blog.model.User;
import com.letspeer.util.BlogUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/users/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private UserDao dao;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		dao = new UserDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI();
		if (path.endsWith("/users/register") || path.endsWith("/users/register/")) {
			BlogUtil.RenderPage("userRegister", null, request, response);
		}

		if (path.endsWith("/users/login") || path.endsWith("/users/login/")) {
			BlogUtil.RenderPage("userLogin", null, request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		String passwordConfirm = request.getParameter("txtConfirmPassword");
		List<String> errorList = new ArrayList<String>();
		String path = request.getRequestURI();
		if (path.endsWith("/users/register") || path.endsWith("/users/register/")) {
			if (firstName == null || firstName.trim().equals("")) {
				errorList.add("Please enter your first name");
			}

			if (lastName == null || lastName.trim().equals("")) {
				errorList.add("Please enter your last name");
			}

			if (email == null || email.trim().equals("")) {
				errorList.add("Please enter your email address");
			}

			if (password == null || password.trim().equals("")) {
				errorList.add("Please enter your password");
			}

			if (passwordConfirm == null || passwordConfirm.trim().equals("")) {
				errorList.add("Please repeat your password");
			}

			if (password != null && !password.trim().equals("") && passwordConfirm != null
					&& !passwordConfirm.trim().equals("")) {
				if (!password.equals(passwordConfirm)) {
					errorList.add("Password and password confirmation doesnot match");
				}
			}

			if (!BlogUtil.validate(email)) {
				errorList.add("Please enter valid email address");
			}

			if (errorList.size() > 0) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("errorList", errorList);
				BlogUtil.RenderPage("userRegister", hm, request, response);

			}

			User u = new User();
			u.setAboutMe("");
			u.setDeleted(false);
			u.setEmail(email);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setPassword(password);
			u.setProfilePicture("");

			int uid = dao.addUser(u);

			if (uid > 0) {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("message", "Thank you for registering in our site please check your email");
				BlogUtil.RenderPage("userRegister", result, request, response);
			}

		}
		
		if(path.endsWith("/users/login") || path.endsWith("/users/login/")) {
			
			if (email == null || email.trim().equals("")) {
				errorList.add("Please enter your email address");
			}

			if (password == null || password.trim().equals("")) {
				errorList.add("Please enter your password");
			}
			
			if (!BlogUtil.validate(email)) {
				errorList.add("Please enter valid email address");
			}
			
			if (errorList.size() > 0) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("errorList", errorList);
				BlogUtil.RenderPage("userLogin", hm, request, response);

			}
			
			
			User u = dao.getUserByEmail(email) ; 
			
			if(u==null) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				errorList.add("No user exist with this email") ; 
				hm.put("errorList", errorList);
				BlogUtil.RenderPage("userLogin", hm, request, response);
				return ; 
			}
			
			if(!u.getPassword().equals(password)) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				errorList.add("Email/password mismatch") ; 
				hm.put("errorList", errorList);
				BlogUtil.RenderPage("userLogin", hm, request, response);
				return ; 
			}
			
			request.getSession().setAttribute("user", u);
			
			response.sendRedirect(request.getSession().getAttribute("LAST_URL").toString());
			
			
			
			
		}

	}

}
