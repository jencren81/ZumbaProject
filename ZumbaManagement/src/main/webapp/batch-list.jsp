<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zumbamanagement.model.Batch" %>
<%@ page import="java.util.List" %>
<%@page import = "java.sql.*"%>
<%@page import = "java.io.IOException" %>




<html>
<head>
<title>Batch Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.zumbamanage.net" class="navbar-brand"> Batch
					Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/BServlet?action=listb"
					class="nav-link">Batches</a></li>
					<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Participants</a></li>
			</ul>
			</nav>
	</header>
	<br>

		<div class="row">
	

		<div class="container">
			<h3 class="text-center">List of Classes</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/BServlet?action=newb" class="btn btn-success">Add
					New Batch</a>
			</div>
			<br>
			
<table class="table table-bordered">

				<thead>
					<tr>
						<th>ID</th>
						<th>Option</th>
						<th>Days</th>
						<th>Time</th>
						<th>Actions</th>
					</tr>
				</thead>
	<tbody>
				
			
	
				
						
	 <% for (Batch batch : (List<Batch>) request.getAttribute("listBatches")) { %>
      <tr>
       	<td><%= batch.getId() %></td>
        <td><%= batch.getOption() %></td>
        <td><%= batch.getDays() %></td>
        <td><%= batch.getTime() %></td>
      
  
        
         <td>
     <a href="BServlet?action=editb&id=<%= batch.getId() %>">Edit</a>
   
 	     &nbsp;&nbsp;&nbsp;&nbsp;
 	<a href="BServlet?action=deleteb&id=<%= batch.getId()%>">Delete</a>
    </td>
      </tr>
    <% } %>

  </tbody>
</table>	

</body>
</html>