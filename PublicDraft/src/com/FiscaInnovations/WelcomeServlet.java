package com.FiscaInnovations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({ "/WelcomeServlet", "/welcome" })
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeServlet() {
        super();    
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();    	
	
    	Cookie[] sessionCook = request.getCookies();
    	if(sessionCook != null)
    		for(Cookie c : sessionCook)
    			if(c.getName() == "lang") {
    				if(c.getValue().equals("fr")) {
    					session.setAttribute("lang", "fr");
    					response.getWriter().println("Avant forward.");
    					response.sendRedirect(request.getContextPath()+"/home");
    					response.getWriter().println("Après forward.");
    				}
    				else if(c.getValue().equals("en")) {
    					session.setAttribute("lang", "en");
    					response.getWriter().println("Before forward.");
    					response.sendRedirect(request.getContextPath()+"/home");    					
    					response.getWriter().println("After forward.");
    				}    				    				
    			}
    				    				    	
    	request.getRequestDispatcher("/WEB-INF/welcome.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Cookie langCookie;
		
		if(request.getParameter("langBtn").equals("Français")) {
			langCookie = new Cookie("lang", "fr");
			langCookie.setMaxAge(29030400);
			response.addCookie(langCookie);
			session.setAttribute("lang", "fr");
		}
		else {
			langCookie = new Cookie("lang", "en");
			langCookie.setMaxAge(29030400);
			response.addCookie(langCookie);
			session.setAttribute("lang", "en");			
		}					
				
		response.sendRedirect(request.getContextPath()+"/home");
	}

}
