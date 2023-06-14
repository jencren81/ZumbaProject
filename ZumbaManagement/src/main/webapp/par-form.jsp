<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zumbamanagement.model.Participant" %>
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
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Participants</a></li>
					<li><a href="<%=request.getContextPath()%>/BServlet?action=listb"
					class="nav-link">Batches</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<%	
   				 Participant participant = (Participant) request.getAttribute("participant");%>
				<% if (participant != null) { %>
					<form action="update" method="post">
				<% } %>
				<% if (participant == null) { %>
					<form action="insert" method="post">
				<% } %>

				<caption>
					<h2>
						<% if (participant != null) { %>
            				Edit Participant
            			<% } %>
						<% if (participant == null) { %>
            				Add New Participant
            			<% } %>
					</h2>
				</caption>

				<% if (participant != null) { %>
					<fieldset class="form-group">
					<label>ID</label> <input type="text" value="<%=participant != null ? participant.getCid() : ""%>" class="form-control" name="cid">
				</fieldset>
				<% } %>

				<fieldset class="form-group">
					<label>Participant First Name</label> <input type="text" value="<%=participant != null ? participant.getFirstname() : ""%>" class="form-control" name="firstname">
				</fieldset>
				<fieldset class="form-group">
					<label>Participant Last Name</label> <input type="text" value="<%=participant != null ? participant.getLastname() : ""%>" class="form-control" name="lastname">
				</fieldset>

				<fieldset class="form-group">
					<label>Participant Email</label> <input type="text" value="<%=participant != null ? participant.getEmail() : ""%>" class="form-control" name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Participant Phone</label> <input type="text" value="<%=participant != null ? participant.getPhone() : ""%>" class="form-control" name="phone">
				</fieldset>
				<fieldset class="form-group">
					<label>Participant Batch</label> <input type="text" value="<%=participant != null ? participant.getBatch() : ""%>" class="form-control" name="batch">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
