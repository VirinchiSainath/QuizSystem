<%@ page import="com.quizsystem.entity.Question" %>
<%@ page import="com.quizsystem.entity.Answer" %>
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
	Question thisquestion = (Question)request.getAttribute("thisquestion");
%>
<% if (thisquestion != null ) { %>
<form action="QuizServlet?action=updateQuestion&questionId=<%=thisquestion.getId() %>&quizId=<%=thisquestion.getQuizId() %>" method ="post">
<div align="center">
	<table>
		<tr><td>Q:</td><td>&nbsp;&nbsp;<textarea rows="2" cols="75" name="description"><%=thisquestion.getDescription() %> </textarea> </td></tr>
		<tr><td colspan="2"><ol type="a" >
						<% if(thisquestion.getAnswers() != null) { %>
						<% for(Answer ans : thisquestion.getAnswers()) { %>
						<% Answer temp = ans; %>
							<li> 
								<input value="<%=ans.getAnswer() %>" name="<%="descr"+ans.getId() %>" /> &nbsp;&nbsp;
								Correct Answer <input type="checkbox" name="<%="correctness"+ans.getId()%>" <%=ans.getCorrectness().equals("Y")?"checked":"" %> />
							</li>  <br /><br />
						<% } } %>
				</ol> </td> </tr>
	</table> <br /> <br />
	<input type="Submit" value="Done" /> OR <input type="text" name="newAnswer" /><input type="Submit" value="Add Answer"/>
</div>

</form>
<% } %>
<% if(thisquestion == null) { %>
	<% int quizId = Integer.parseInt(request.getParameter("quizId")); %>
	<div align="center">
	<form action="QuizServlet?action=addNewQuestion&quizId=<%=quizId %>" method="post">
		<table>
		<tr><td>Q:</td><td>&nbsp;&nbsp;<textarea rows="2" cols="75" name="description"></textarea> </td></tr>
		</table> <br /> <br />
	<input type="Submit" value="Done" /> OR <input type="text" name="newAnswer" /><input type="Submit" value="Add Answer"/>
	</form>
	</div>
<% } %>

</body>
</html>