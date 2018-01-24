<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.List, com.quizsystem.entity.Student" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp"%>
<%
	List<Student> students= (List<Student>)request.getAttribute("students");

%>
<br /><br /><br /><br /><br /><br />

<table width="50%" align ="center" border="0">
	<tr>
		<td><a href="addStudent.jsp"><input type="button" value="Add" /></a></td>
		<td colspan="6" align="right">
			<form action="StudentServlet?action=searchStudents" method="POST">
				<input type="text" name="search" />&nbsp;&nbsp;<input type="Submit" value="Search" />
			</form>
		</td>
	</tr>
	<tr>
		<td colspan="6">&nbsp;</td>
	</tr>	
	<tr bgcolor="grey">
		<th>Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>E-mail</th>
		<th>Username</th>
		<th>Password</th>
		<th>&nbsp;</th>
	</tr>
<% 
for(Student s: students ){
%>
<tr bgcolor="lightgreen">
	<td><%= s.getId() %></td>
	<td><%= s.getFirstName() %> </td>
	<td><%= s.getLastName() %> </td>
	<td><%= s.getEmail() %> </td>
	<td><%= s.getUserId()  %></td>
	<td><%= s.getPassword() %></td>
	<td><a href="StudentServlet?action=editStudent&id=<%=s.getId() %>"><input type="button" value="Edit" /></a>&nbsp;&nbsp;<a href="StudentServlet?action=deleteStudent&id=<%=s.getId() %>"><input type="button" value="Delete" /></a>
</tr>
<%	
}
%>
</table>


</body>
</html>