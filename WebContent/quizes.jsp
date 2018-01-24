<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.quizsystem.entity.*" %>
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
<%
	List<Quiz> qlist = (List<Quiz>)request.getAttribute("qList");
	HashMap<Integer, Integer> quizAttempts = (HashMap<Integer, Integer>)request.getAttribute("quizAttempts");
%>
<table style="margin: 0px 200px;">
<tr >
	<% if(request.getSession().getAttribute("userRole").equals("professor") ) {%>
		<th align="left"><a href="addQuiz.jsp"><input type="button" value="Add"></a></th>
	<% } %>
		<th colspan="5" align="right">
			<form action="" method="POST"> 
				<input type="text" name="search" /><input type="button" value="Submit" />
			</form>
		</th>
</tr>
<tr bgcolor="orange">
	<td>Id</td>
	<td>Quiz Name</td>
	<td>Number of Questions</td>
	<td> Duration </td>
	<td> Show Answers after test or not</td>
	<td>Score</td>
</tr>
<% 
	if(qlist != null) {
		for(Quiz q : qlist) {
%>
<tr bgcolor="grey">
	<td><%=q.getId() %></td>
	<td><%=q.getName() %></td>
	<td><%=q.getnOfQues() %></td>
	<td><%=q.getDuration() %></td>
	<td><%=q.getShowAns() %></td>
	<td>
		<% if(request.getSession().getAttribute("userRole").equals("professor") ) {%>
		<a href="QuizServlet?action=editQuiz&id=<%=q.getId()%>"> <input type="Submit" value="Edit" /></a>
		<a href="QuizServlet?action=deleteQuiz"> <input type="Submit" value="Delete" /></a>	
		<% } %>
		<% if(request.getSession().getAttribute("userRole").equals("student") ) { %>		
			<% Integer quizScore = quizAttempts.get(q.getId()); %>
			<% if (quizScore !=null) { %>
				<p><%=quizScore %></p>
			<% } %>
			<% if (quizScore == null) { %> 
				<form action="startQuiz.jsp" method="post">
					<input type="hidden" value="<%=q.getId() %>" name="quizId" />
					<input type="Submit" value="Take Quiz" />
				</form>
			<% } %>
			
		<% } %>
	</td>
</tr>
<%
	} } 

%>
</table>

</body>
</html>