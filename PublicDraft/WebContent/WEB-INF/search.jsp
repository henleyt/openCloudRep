<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.FiscaInnovations.SearchResults" %>
<%@page import="com.FiscaInnovations.lang.LSLsearch" %>

<% LSLsearch txtSearch = (LSLsearch) request.getAttribute("langSearch"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=txtSearch.toString(0)%></title>
</head>
<body>

<%@include  file="/WEB-INF/home.jsp" %>

<form action="">
	<%=txtSearch.toString(1)%>
	<input type="text" id="searchTerms" name="searchTerms"><input id="submitBtn" type="submit" value="<%=txtSearch.toString(2)%>">		
	<br>	
</form>
<br>
<div id="searchResult"><%= ((SearchResults) request.getAttribute("resultsBean")).getResultsHTML() %></div>

<%@include  file="/WEB-INF/foot.jsp" %>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">

var runningRequest = false;
var request;

$('input#submitBtn').click(function(event){

	var $q = $('input#searchTerms');
	
    //Abort opened requests to speed it up
    if(runningRequest){
        request.abort();
    }
    
    $.ajax({    	
        url:'search?searchTerms='+$q.val(),   
        type:'post',   
        dataType: 'json',   
        success: function(data) {
     	   resultHtml = data.resultsDivHTML;
        },   
        
        complete: function(){
     	    $('div#searchResult').html(resultHtml);    		    		
        }     	
     });
    
 });


//Identify the typing action
$('input#searchTerms').keyup(function(event){
	event.preventDefault();
    var $q = $(this); //get the letter
    
    //Abort opened requests to speed it up
    if(runningRequest){
        request.abort();
    }
    
    var resultHtml = '';
    
    $.ajax({
    	
       url:'search?&searchTerms='+$q.val(),   
       type:'post',   
       dataType: 'json',   
       success: function(data) {
    	   resultHtml = data.resultsDivHTML;
       },   
       
       complete: function(){
    	    $('div#searchResult').html(resultHtml);    		    		
       }
    	
    });   
         
});
      
</script>
</body>
</html>