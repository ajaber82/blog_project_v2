package com.letspeer.util;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;

public class BlogUtil {
	
	public static void RenderPage(String tileName, HashMap<String, Object> requestObject,HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext applicationContext = ServletUtil.getApplicationContext(request.getServletContext());
		TilesContainer container = TilesAccess.getContainer(applicationContext);
		
		ServletRequest servletRequest = new ServletRequest(applicationContext, request, response);
		if(requestObject != null) {
			for(Entry<String, Object> entry : requestObject.entrySet()) {
				servletRequest.getRequest().setAttribute(entry.getKey(), entry.getValue());
			}
			
		}
		
		container.render(tileName, servletRequest);
	}
	
	public static boolean validate(String emailStr) {
		Pattern validEmailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher =  validEmailRegex.matcher(emailStr);
        return matcher.find();
}

}
