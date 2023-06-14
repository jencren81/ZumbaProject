package com.zumbamanagement.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ZumbaLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/zumba","root","6781jjF!");
				String n=request.getParameter("txtUsername");
				String p=request.getParameter("txtPassword");
				
				PreparedStatement ps=con.prepareStatement("select name from user where name=? and password=?");
				ps.setString(1, n);
				ps.setString(2,  p);
				ResultSet rs=ps.executeQuery();
				
				if(rs.next()) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("list");
					dispatcher.forward(request, response);
				}else {
					out.println("<font color=red size=18>Login Failed<br>");
					out.println("<a href=Login.jsp>Click here to try again</a>");
				}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		
	}

}
