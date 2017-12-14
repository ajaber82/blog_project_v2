package com.letspeer.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class BlogFilter
 */
@WebFilter("/*")
public class BlogFilter implements Filter {

    /**
     * Default constructor. 
     */ 
    public BlogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   
	   HttpServletRequest servletRquest = (HttpServletRequest) request ; 
	   HttpServletResponse servletResponse = (HttpServletResponse) response ; // we cast the types 
	   String path = servletRquest.getRequestURI(); 
	   if(servletRquest.getMethod().equalsIgnoreCase("get") && !path.contains("login") && !path.contains("register")) {
		   servletRquest.getSession().setAttribute("LAST_URL", path);
	   }
	   if(path.contains("/assets/")) {
		   chain.doFilter(request, response);
	   }

	   if(path.endsWith("/blog/new-blog") || path.endsWith("/users/profile/edit-profile")) {
		   if(servletRquest.getSession().getAttribute("user") == null) {
			   servletResponse.sendRedirect(servletRquest.getContextPath() + "/users/login");
		   }
	   }
	   	
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
