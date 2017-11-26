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
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI() ; 
		if(path.endsWith("/users/register")||path.endsWith("/users/register/")) {
			BlogUtil.RenderPage("userRegister", null, request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("txtFirstName") ; 
		String lastName = request.getParameter("txtLastName") ;
		String email = request.getParameter("txtEmail") ; 
		String password = request.getParameter("txtPassword") ; 
		String passwordConfirm = request.getParameter("txtConfirmPassword") ; 
		List<String> errorList = new ArrayList<String>() ; 
		
		if(firstName == null || firstName.trim().equals("")) {
			errorList.add("Please enter your first name") ; 
		}
		
		if(lastName == null || lastName.trim().equals("")) {
			errorList.add("Please enter your last name") ; 
		}
		
		if(email == null || email.trim().equals("")) {
			errorList.add("Please enter your email address") ; 
		}
		
		if(password == null || password.trim().equals("")) {
			errorList.add("Please enter your password") ; 
		}
		
		if(passwordConfirm == null || passwordConfirm.trim().equals("")) {
			errorList.add("Please repeat your password") ; 
		}
		
		if(password!=null && !password.trim().equals("") && passwordConfirm!=null && !passwordConfirm.trim().equals("")) {
			if(!password.equals(passwordConfirm)) {
				errorList.add("Password and password confirmation doesnot match") ; 
			}
		}
		
		if(!BlogUtil.validate(email)) {
			errorList.add("Please enter valid email address") ; 
		}
		
		if(errorList.size()>0) {
			HashMap<String, Object> hm = new HashMap<String, Object>() ; 
			hm.put("errorList", errorList) ; 
			BlogUtil.RenderPage("userRegister", hm, request, response);
			
		}
		
	}

}
