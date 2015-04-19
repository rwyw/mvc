<%@page import="com.zj.mvcapp.domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MVC</title>
</head>
<body>

	<%-- <%
		Object msg = request.getAttribute("message");
		if(msg != null){
	%>
			<br>
			<font color="red"><%= msg %></font>	
			<br><br>
 --%>
	<%
		Customer customer = (Customer)request.getAttribute("customer");
	%>
	<form action="update.do" method="post">
		
		<input type="hidden" name="id" value="<%=customer.getId()%>"/>

		<table>
			<%-- <tr>
				<td>ID:</td>
				<td><input type="text" name="id"
					value="<%= customer.getId() %>"/></td>
			</tr> --%>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name" 
					value="<%= customer.getName() %>"/></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="text" name="age" 
					value="<%= customer.getAge() %>"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" 
					value="<%= customer.getAddress() %>"/></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone" 
					value="<%= customer.getPhone() %>"/></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Submit"/></td>
			</tr>
		</table>
	
	</form>

</body>
</html>