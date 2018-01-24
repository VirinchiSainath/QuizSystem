<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %> <br /> <br /> <br /> <br /> 
<div style="margin: 0px 200px;" align="left">
<form action="QuizServlet?action=addQuiz" method="POST">
	Name <input type="text" name="name" /> <br /> <br /> 
	Number of Questions <input type="text" name="nOfQues" /><br /> <br /> 
	Duration (in minutes) <input type="text" name="duration" /><br /> <br /> 
	Show Answers after test <input type="radio" name="showAns" value="Y" />Yes<input type="radio" name="showAns" value="N"/>No<br /> <br />
	<input type="Submit" value="Submit" /><br /> 
</form>
</div>

</body>
</html>