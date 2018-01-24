<%@ page import="java.util.List" %>
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
	List<Answer> ansList = (List<Answer>)request.getAttribute("ansList");
	QuesSubmission quesSub = (QuesSubmission)request.getAttribute("quesSub");
	Integer index = (Integer)request.getAttribute("index");
	Integer size = (Integer)request.getSession().getAttribute("size");
	
%>

<% if (quesSub == null) { %>
<div align ="center" >
	<h1> All the best ! </h1>
	<a href="QuizServlet?action=takeQuiz&quizId=<%=request.getParameter("quizId") %>" ><input type="button" value="Start Quiz"/></a>
</div>
<% } %>


<!-- quiz questions -->

<% if (quesSub != null) { %> <!-- if Question not null opening -->

<table align="center">
		<tr>
			<td>Q<%=index+1 %></td>
			<td><%=quesSub.getQuestionDesc() %></td>
		</tr>
</table>

<form action="QuizServlet?action=loadQuestion&index=<%=index %>" method="post">
<div align="center">
	<ul>
	<% for (Answer a : ansList) { %>
		<li style="float: none;"><%=a.getAnswer() %>
			<input type="checkbox" name="<%="correctness"+a.getId()%>" <%=a.getCorrectness().equals("Y")?"checked":"" %> />
		</li> <br />
	<% } %>
	</ul>
</div>
	<!-- question navigation -->
<table align="center">
	<tr>
		<% if(index > 0) { %>
		<td><input type="Submit" name="prev" value="prev" /></td>
		<% } %>
		<td><input type="Submit" value="Review" /></td>
		<% if(index != size-1) {%>
		<td><input type="Submit" name="next" value="next" /></td>
		<% } %>
	</tr>
	<tr>
		<td colspan="3"><input type="Submit" name="finish" value="Complete Test" /></td>
</table>
</form>


<% } %> <!-- if question not null closing bracket -->
</body>
</html>