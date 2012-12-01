package com.FiscaInnovations;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;

import com.FiscaInnovations.lang.LSLsearch;

@WebServlet({"/SearchServlet", "/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Vérifier pour scope session. Seulement 1 objet de type
	
	String[] someData;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        
        someData = new String[] { "Tommy Henley", "Marc Dumoulin", "Jean Dumoulin" };
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{									
		
		HttpSession session = request.getSession();			
		if(session == null || (String) session.getAttribute("lang") == null) {		
			response.sendRedirect(request.getContextPath()+"/welcome");
			session.invalidate();
			return;
		}	
		else if((Object) request.getAttribute("langHome") == null || (Object) request.getAttribute("langFoot")== null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
			
		ArrayList<String> results = doSearch(request.getParameter("searchTerms"));
		String lang = session.getAttribute("lang").toString();				
		
		LSLsearch searchText = new LSLsearch(lang, /*true = show refTag*/ false);		
		
		SearchResults resultsBean = new SearchResults();				
		resultsBean.setResultsHTML(resultsToHtml(results, lang));
		
		request.setAttribute("langSearch", searchText);
		request.setAttribute("resultsBean", resultsBean);
				
		request.getRequestDispatcher("/WEB-INF/search.jsp").include(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();			
		if(session == null || (String) session.getAttribute("lang") == null) {		
			response.sendRedirect(request.getContextPath()+"/welcome");
			session.invalidate();
			return;
		}		
		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out= response.getWriter();   

		ArrayList<String> results = doSearch(request.getParameter("searchTerms"));
		String lang = (String) session.getAttribute("lang");
		
		json.put("resultsDivHTML", resultsToHtml(results,lang));
		
		out.print(json);
		out.flush();
	}
	
	protected ArrayList<String> doSearch(String srchTerms) {	
		if(srchTerms != null && !srchTerms.equals("")) {			
			ArrayList<String> newResults = new ArrayList<String>();
			
			for(String item : someData)
				if((item.toLowerCase()).contains(srchTerms.toLowerCase()))
					newResults.add(item);
			
			return newResults;
		}
		else
			return null;						
	}

	protected String resultsToHtml(ArrayList<String> results, String lang) {
				
		String resultsHtml;
		
		String[] langString = new String[5];		
		int nbResults;
		
		if(lang != null && lang.equals("fr")) {
			langString[0] = "Résultat de la recherche";
			langString[1] = "correspondance trouvée";
			langString[2] = "correspondances trouvées";
			langString[3] = "Aucune correspondance";
			langString[4] = "Effectuez une nouvelle recherche";			
		}
		else { //en
			langString[0] = "Search results";
			langString[1] = "match found";
			langString[2] = "matches found";
			langString[3] = "No match";
			langString[4] = "Make a new search";			
		}		
		
		if(results != null)	{
			nbResults = results.size();		
			
			resultsHtml = "<p>"+langString[0]+": ";
			
			if(nbResults != 0) {				
				if(nbResults == 1)
					resultsHtml += nbResults +" " + langString[1]+".<br>";
				else
					resultsHtml += nbResults +" " + langString[2]+".<br>";
				
				for(int i=0;i<nbResults;i++)
					resultsHtml += "<br>"+(i+1)+": "+results.get(i)+".";				
				resultsHtml += "</p>";
			}
			else
				resultsHtml += langString[3]+".</p>";
		}
		else
			resultsHtml = "<p>"+langString[4]+".</p>";
		
		return resultsHtml;
	}
}
