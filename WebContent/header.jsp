<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Quiz System</title>
</head>
<link type="text/css" rel="stylesheet" href="stylesheet.css" />
<link rel="shortcut icon" href="icon.ico">
<body>
	<%
		String firstname = (String) session.getAttribute("firstname");
		String userRole = (String) session.getAttribute("userRole");
	%>
	<header>
		<h1> 
			Online Quiz Hosting <br /> 
			<div class="tag_line"> Host your quiz for your students </div>
		</h1>
		<!-- <navigantion-bar>
			<ul>
			  <li><a href="#home">Home</a></li>
			  <li><a href="#news">News</a></li>
			  <li><a href="#contact">Contact</a></li>
			  <li><a href="#about">About</a></li>
			</ul>
		</navigantion-bar> -->
	</header>
	<div width="20%" height="80px">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<!-- <td colspan="2" height="1"><img src="images/logo.jpg"
					width="100px" height="100px"></td>
				<td> -->
					<table border="0">
						<tr>
							<td align="right" width="1100px">
								<%
									if (firstname == null) {
								%> <a href="login.jsp">Login</a><br> <a href="signup.jsp"
								class="right">Professor Sign Up</a><br> <a href="index.jsp">Home</a> <%
 								} else {
								%> Welcome&nbsp;<%=firstname%><br> <a
								href="LoginServlet?action=logout" class="right">Logout</a><br>
								<%
									}
								%>
							</td>
						</tr>
					</table>
			</tr>
		</table>
	</div>
	<!-- <div width="100%" height="10px" style="background-color: lightgrey">-->
	<ul class="nav">

		<%
			if (userRole == null) {
		%>
		<li class="dropdown"><a href="aboutus.jsp" class="dropbtn">About
				Us</a></li>
		<li class="dropdown"><a href="contactus.jsp" class="dropbtn">Contact
				Us</a></li>
		<%
			} else if (userRole.equals("student")) {
		%>
		<li class="dropdown"><a href="QuizServlet?action=quizlist" class="dropbtn">Quizes</a></li>
		<li class="dropdown"><a href="aboutus.jsp" class="dropbtn">About Us</a></li>
		<li class="dropdown"><a href="contactus.jsp" class="dropbtn">Contact Us</a></li>
		<%
			} else {
		%>
		<li class="dropdown"><a class="dropbtn">Students</a>
			<div class="dropdown-content">
				<a href="StudentServlet?action=listStudents">Students</a> <a
					href="books.jsp">PDF</a> <a href="books.jsp">News Articles</a>
			</div></li>
		<li class="dropdown"><a href="QuizServlet?action=quizlist" class="dropbtn">Quizes</a></li>
		<li class="dropdown"><a href="aboutus.jsp" class="dropbtn">About
				Us</a></li>
		<li class="dropdown"><a href="contactus.jsp" class="dropbtn">Contact
				Us</a></li>
		
		<%
			}
		%>
	</ul>


</body>
</html>