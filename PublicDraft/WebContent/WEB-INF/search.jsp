<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.FiscaInnovations.SearchResults" %>
<%@page import="com.FiscaInnovations.lang.LSLsearch" %>

<% LSLsearch txt = (LSLsearch) request.getAttribute("langBean"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=txt.toString(0)%></title>
</head>
<body>
<form action="search">
	<%=txt.toString(1)%>
	<input type="text" id="searchTerms" name="searchTerms"><input type="submit" value="<%=txt.toString(2)%>">
	
	<input type="hidden" id="lang" name="lang" value="<%=txt.getLangParam() %>">
	
	<br>	
</form>
<br>
<div id="searchResult"><%= ((SearchResults) request.getAttribute("resultsBean")).getResultsHTML() %></div>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">

var runningRequest = false;
var request;

//Identify the typing action
$('input#searchTerms').keyup(function(e){
    e.preventDefault();
    var $q = $(this); //get the letter
    
    //if it's not a search term return false
//    if($q.val() == ''){
//        $('div#searchResult').html('<p>Entrez une recherche.</p>');
//        return false;
//    }

    //Abort opened requests to speed it up
    if(runningRequest){
        request.abort();
    }
    
    var resultHtml = '';
    
    $.ajax({   
       url:'search?lang='+$('input#lang').val()+'&searchTerms='+$q.val(),   
       type:'post',   
       dataType: 'json',   
       success: function(data) {
    	   resultHtml = data.resultsDivHTML;
       },   
       
       complete: function(){
    	    $('div#searchResult').empty().html(resultHtml);    		    		
       }
    	
    });   
         
});
      
</script>
</body>
</html>