package com.FiscaInnovations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.Cookie;

import com.FiscaInnovations.lang.LSLhome;
import com.FiscaInnovations.lang.LSLfoot;

@WebServlet({ "/HomeServlet", "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//SessionCheck{
		HttpSession session = request.getSession();
		if(session.getAttribute("lang") == null) {
			Cookie[] sessionCook = request.getCookies();
			if(sessionCook == null) {
				response.sendRedirect(request.getContextPath()+"/welcome");
				return;
			}
			else {				 
				boolean redirect = false;
				
				for(int i=0;i<sessionCook.length;i++)
					if(sessionCook[i].getName().equals("lang"))  {
						session.setAttribute("lang", sessionCook[i].getValue().toString());
						redirect = true;
					}
				
				if(redirect) {
					response.sendRedirect(request.getContextPath()+"/welcome");
					return;
				}

			}
		}				
		//}SessionCheck
		
		LSLhome langHome = new LSLhome(session.getAttribute("lang").toString(), false);
		LSLfoot langFoot = new LSLfoot(session.getAttribute("lang").toString(), false);
		
		request.setAttribute("langHome", langHome);
		request.setAttribute("langFoot", langFoot);
		
		request.getRequestDispatcher("/search").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String langParam = request.getParameter("setLang");
		
		Cookie newLangCookie = new Cookie("lang", langParam);
		newLangCookie.setMaxAge(29030400);
		response.addCookie(newLangCookie);
		request.getSession().setAttribute("lang", langParam);
		
		response.sendRedirect(request.getContextPath()+"/home");
	}

}
