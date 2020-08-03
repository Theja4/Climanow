<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //its for HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //Http 1.0
			
	if(session.getAttribute("email")==null){
		response.sendRedirect("login.jsp");
	}

%>
	Hi ${email}
	
	<form action="<%=request.getContextPath()%>/logout" method="post">
		<input type="submit" value="logout">
	</form>
</body>
</html>