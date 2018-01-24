<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form action="ProfessorServlet?action=signup" method="post">
	
		<br> <br> <br>
		<table border="0">
			<tr>
				<td width="400px" height="200"><img src="images/signup.jpg"
					width="200px" height="250px" align="left"></td>
				<td align="left" width="800px" height="200">
					<table>
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
							<td>Enter Login ID</td>
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
				</td>
			</tr>
		</table>
	</form>

</body>
</html>