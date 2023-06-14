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
				<li><a href="<%=request.getContextPath()%>/BServlet?action=listb"
					class="nav-link">Batches</a></li>
					<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Participants</a></li>
			</ul>
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
		<tbody></tbody>
   				 <%	
   				 Participant participant = (Participant) request.getAttribute("participant");%>
	
					<caption>
					<h2>
					Enter Batch Option For Participants
					</h2>
					</caption>
					
	<form action="select" method="post">
	<fieldset class="form-group">
					<label>Batch</label> <input type="text" class="form-control" name="batch">
				</fieldset>
					<button type="submit" class="btn btn-success">Select</button>
					</form>
			</div>
		</div>
	</div>
</body>
</html>