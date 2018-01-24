<%@ page import="com.quizsystem.entity.*" %>
<%@ page import="java.util.List" %>
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
	List<Question> quesList = (List<Question>)request.getAttribute("quesList");
	int quizId = Integer.parseInt(request.getParameter("quizId"));
%>
<!-- <form>
	<table>
		<tr> <td>Question</td><td><textarea rows="4" cols="100"></textarea></td>
		
	</table>
</form> -->
<% if (quesList != null) { %>
	<p align="center" style="margin:50px;"> No questions added till now </p>
	<% } %>
<form >
	<table align="center"> 
	<% int count=0; %>
	<% for(Question ques : quesList) { %>
		<tr> <td> Question<%=++count %> </td> 
		<td> <%=ques.getDescription() %></td> 
		<td><a href="AnswerServlet?action=showAns&quesId=<%=ques.getId() %>"><input type="button" value="Edit Answers"></a>
			<a href="QuestionServlet?action=editQues&quesId=<%=ques.getId() %>&quizId=<%=quizId %>"><input type="button" value="Edit Question" /></a>
		    <a href="QuestionServlet?action=deleteQues&quesId=<%=ques.getId() %>&quizId=<%=quizId %>"><input type="button" value="Delete Question" /></a>
		</td>
	<% } %>
	</table>
	</form>
	
<div align="center">
	<form action="addQuestion.jsp">
		<input type="hidden" value="<%=quizId %>" name="quizId" />
		<input type="Submit" value="Add Question" /> 
	</form>
</div>

</body>
</html>