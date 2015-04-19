<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MVC</title>
</head>
<body>

	<%
		Object msg = request.getAttribute("message");
		if(msg != null){
	%>
			<br>
			<font color="red"><%= msg %></font>	
			<br><br>
	<%
		}
	%>

	<form action="add.do" method="post">
	
		<table>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name" 
					value="<%= request.getParameter("name") == null ? "" : request.getParameter("name")%>"/></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="text" name="age" 
					value="<%= request.getParameter("age") == null ? "" : request.getParameter("age")%>"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" 
					value="<%= request.getParameter("address") == null ? "" : request.getParameter("address")%>"/></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone" 
					value="<%= request.getParameter("phone") == null ? "" : request.getParameter("phone")%>"/></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Submit"/></td>
			</tr>
		</table>
	
	</form>

</body>
</html>