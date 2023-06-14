<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zumbamanagement.model.Batch" %>
<%@ page import="java.util.List" %>
<html>
<head>
<title>Zumba Management Application</title>
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
				<a href="https://www.zumbamanage.net" class="navbar-brand"> Zumba Management Application </a>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<%	
   				 Batch batch = (Batch) request.getAttribute("batch");%>
				<% if (batch != null) { %>
					<form action="BServlet?action=updateb" method="POST">
				<% } %>
				<% if (batch == null) { %>
					<form action="BServlet?action=insertb" method="POST">
				<% } %>

				<caption>
					<h2>
						<% if (batch != null) { %>
            				Edit Batch
            			<% } %>
						<% if (batch == null) { %>
            				Add New Batch
            			<% } %>
					</h2>
				</caption>

				<% if (batch != null) { %>
					<fieldset class="form-group">
					<label>Batch ID</label> <input type="text" value="<%=batch != null ? batch.getId() : ""%>" class="form-control" name="id">
				</fieldset>
				<% } %>

				<fieldset class="form-group">
					<label>Batch Option</label> <input type="text" value="<%=batch != null ? batch.getOption() : ""%>" class="form-control" name="option">
				</fieldset>
				<fieldset class="form-group">
					<label>Batch Days</label> <input type="text" value="<%=batch != null ? batch.getDays() : ""%>" class="form-control" name="days">
				</fieldset>

				<fieldset class="form-group">
					<label>Batch Time</label> <input type="text" value="<%=batch != null ? batch.getTime() : ""%>" class="form-control" name="time">
				</fieldset>

				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>