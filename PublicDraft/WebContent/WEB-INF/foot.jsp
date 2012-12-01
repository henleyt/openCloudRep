<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.FiscaInnovations.lang.LSLfoot" %>

<% LSLfoot txtFoot = (LSLfoot) request.getAttribute("langFoot"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=txtFoot.toString(0) %></title>
</head>
<body>
	<%=txtFoot.toString(1) %>
</body>
</html>