package com.FiscaInnovations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FiscaInnovations.lang.LSLsearch;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchServlet
 */

@WebServlet({ "/SearchServlet", "/search" })
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
		
		String lang = request.getParameter("lang");
		ArrayList<String> results = doSearch(request.getParameter("searchTerms"));

		LSLsearch searchText = new LSLsearch(lang, /*true = show refTag*/ false);		
		
		SearchResults resultsBean = new SearchResults();				
		resultsBean.setResultsHTML(resultsToHtml(results, lang));
		
		request.setAttribute("langBean", searchText);
		request.setAttribute("resultsBean", resultsBean);
			
		request.getRequestDispatcher("/WEB-INF/search.jsp").include(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out= response.getWriter();   

		ArrayList<String> results = doSearch(request.getParameter("searchTerms"));
		String lang = request.getParameter("lang");
		
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
