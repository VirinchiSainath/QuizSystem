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
<br> <br> <br>
<form action="StudentServlet?action=addStudent" method="POST">
	<table width="40%" align="center">
		<tr>
			<td>Enter your first name</td>
			<td><input type="text" name="firstName"><br></td>
		</tr>
		<tr>
			<td>Enter your last name</td>
			<td><input type="text" name="lastName"><br></td>
		</tr>
		<tr>
			<td>Enter Email</td>
			<td><input type="text" name="email"><br></td>
		</tr>
		<tr>
			<td>Enter Username</td>
			<td><input type="text" name="userId"><br></td>
		</tr>
		<tr>
			<td>Enter your Password</td>
			<td><input type="password" name="password"><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="submit" value="Submit" /></td>
		</tr>
	</table>
				
	</form>

</body>
</html>