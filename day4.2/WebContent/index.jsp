<%@page import="com.zj.mvcapp.domain.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MVC</title>
<script type="text/javascript" src="scripts/jquery-1.11.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("确定要删除 "+ content +" 信息吗？");
			return flag;
		});
	});
</script>
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); %>
	<form action="query.do" method="post">
		
		<table>
			<tr>
				<td>Id:</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"/></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone"/></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Query"/></td>
				<td><a href="addCustomer.jsp">Add new Customer</a></td>
			</tr>
		</table>
	</form>
	
	<br><br>
	
	<%
		List<Customer> customers = (List<Customer>)request.getAttribute("customers");
	
		if(customers != null && customers.size() > 0){
	%>
		
		<hr>
		<br><br>
			<table border="1" cellpadding="10" cellspacing="0">
				<tr>
					<th>ID</th>
					<th>CUSTOMERNAME</th>
					<th>AGE</th>
					<th>ADDRESS</th>
					<th>PHONE</th>
					<th>UPDATE</th>
					<th>DELETE</th>
				</tr>
				
				<%
					for(Customer customer: customers){
				%>
					<tr>
						<td><%= customer.getId() %></td>
						<td><%= customer.getName() %></td>
						<td><%= customer.getAge() %></td>
						<td><%= customer.getAddress() %></td>
						<td><%= customer.getPhone() %></td>
						<td><a href="edit.do?id=<%= customer.getId()%>">UPDATE</a></td>
						<td><a href="delete.do?id=<%=customer.getId()%>" class="delete">DELETE</a></td>
					</tr>
				<%	
					}
				%>
			</table>
	<%		
		}
	%>

</body>
</html>