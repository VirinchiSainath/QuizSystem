<%@ page import="com.quizsystem.entity.Quiz" %>
<%@ page import="com.quizsystem.entity.Answer" %>
<%@ page import="com.quizsystem.entity.Question" %>
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
	Quiz q = (Quiz) request.getAttribute("quiz");
	List<Question> quesList = (List<Question>) request.getAttribute("quesList");
	//List<Answer> ansList = (List<Answer>) request.getAttribute("ansList");
%>

<form action="QuizServlet?action=updateQuiz&id=<%=q.getId() %>" method="post">
	<table align="center">
		<tr> <td>Name</td> <td><input type="text" value="<%=q.getName() %>" name="name"></td> </tr>
		<tr> <td>Number of Question</td> <td><input type="text" value="<%=q.getnOfQues() %>" name="nOfQues" /></td></tr>
		<tr> <td>Duration</td> <td><input type="text" value="<%=q.getDuration() %>" name="duration" /></td> </tr>
		<tr> <td>Show Answers or Not</td><td><input type="radio" name="showAns" value="Y" <%=q.getShowAns().equals("Y")?"checked":"" %> />Yes&nbsp;&nbsp;
											 <input type="radio" name="showAns" value="N" <%=q.getShowAns().equals("N")?"checked":"" %>/>No</td> </tr>
		<tr> <td colspan="2" align="center"> <input type="Submit" value="Update" /> </td>
	</table>
</form>
<br /><br /><br /><br />

<% if(quesList == null) { %>
<div align="center"	>
	<a href="QuizServlet?action=showQandA&quizId=<%=q.getId() %>" ><input type="button" value="Show Questions" /></a>
</div>
<% } %>

<div align="center" >
	<a href="editQuestion.jsp?quizId=<%=q.getId()%>"><input type="button" value="Add New Question" /></a>
</div>

<table align="center"> 
<% if(quesList != null) { int quesCount = 0; %> 
<% for(Question ques : quesList) {%>
	<tr align="left"><td  colspan="2"><a href="QuizServlet?action=editQues&quesId=<%=ques.getId() %>" ><input type="button" value="Edit" /></a></td> </tr>
	<tr><td>Q<%=++quesCount %> </td> <td><%=ques.getDescription()%> </td> </tr>
	<tr><td>&nbsp;</td> <td><ol type="a">
								<% if(ques.getAnswers() != null) {%>
								<% for(Answer ans : ques.getAnswers() ){ %>
								 	<li> <%=ans.getAnswer() %> </li><br />
								 <% } } %>
								 </ol> </td>
	</tr>
	
<% } }%>
	
</table>
 
</body>
</html>