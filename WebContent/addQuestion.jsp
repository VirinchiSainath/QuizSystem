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
	int quizId = Integer.parseInt(request.getParameter("quizId"));
%>
<form action="QuestionServlet?action=newQues" method="post">
	<table align="center">
		<tr> 
			<td>Question: </td>
			<td><textarea rows="4" cols="100" name="description"> </textarea> </td>
			<td> <input type="hidden" name="quizId" value="<%=quizId %>" /> </td>
			<td> <input type="submit" value="Submit" /> </td>
		</tr>		
		<!-- <tr>
			<td colspan="3"><a href="AnswerServlet?action=addAnswe" ><input type="button" value="Add Answer" /></a></td> 
		</tr> -->
	</table>
</form>

</body>
</html>