<%@ page import="com.quizsystem.entity.Student" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
<% Student s = (Student) request.getAttribute("student"); %>
<br> <br> <br>
<form action="StudentServlet?action=updateStudent&id=<%=s.getId() %>" method="POST">
	<table width="40%" align="center">
		<tr>
			<td>Enter your first name</td>
			<td><input type="text" name="firstName" value="<%=s.getFirstName() %>"><br></td>
		</tr>
		<tr>
			<td>Enter your last name</td>
			<td><input type="text" name="lastName" value="<%=s.getLastName() %>"><br></td>
		</tr>
		<tr>
			<td>Enter Email</td>
			<td><input type="text" name="email" value="<%=s.getEmail() %>"><br></td>
		</tr>
		<tr>
			<td>Enter Username</td>
			<td><input type="text" name="userId" value="<%=s.getUserId()%>"><br></td>
		</tr>
		<tr>
			<td>Enter your Password</td>
			<td><input type="password" name="password" value="<%=s.getPassword() %>"><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="submit" value="Submit" /></td>
		</tr>
	</table>
				
	</form>

</body>
</html>