<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div align=center>
<h1>User Login</h1>
</div>
<form action=ZumbaLoginServlet method=post>
<table>
<tr><td>Enter Name:</td><td><input type=text name=txtUsername></td></tr>
<tr><td>Enter Password:</td><td><input type=password name=txtPassword></td></tr>
<tr><td><input type=submit value=Login></td><td><input type=reset></td></tr>
</table>
</form>
</body>
</html>