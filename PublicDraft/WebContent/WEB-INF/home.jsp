<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.FiscaInnovations.lang.LSLhome" %>

<% LSLhome txtHome = (LSLhome) request.getAttribute("langHome"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=txtHome.toString(0) %></title>
</head>
<body>
	<table>
		<tr>
			<td>
				<%=txtHome.toString(1)%>
			</td>
			<td>
				<%=txtHome.getLangParam()%>
				<a href="" id="linkSetLang1" name="<%=txtHome.getLangParamNext(1)%>"><%=txtHome.getLangParamNext(1)%></a>							
			</td>
		</tr>	
	</table>
	
	<br/>
	
	<script type="text/javascript" src="jquery.js"></script>
	<script type="text/javascript">
	
		var runningRequest = false;
		var request;
		
		$("a").click(function(event){
		
			var $lang = $(this).attr("name");						
			
		    //Abort opened requests to speed it up
		    if(runningRequest){
		        request.abort();
		    }
		    
		    $.post("home", { setLang : $lang }, function(){});
		    
		 });			      
	</script>
	
</body>
</html>