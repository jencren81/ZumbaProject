package com.zumbamanagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;

import com.zumbamanagement.model.Batch;
import com.zumbamanagement.model.Participant;
import com.zumbamangement.dao.ParticipantDao;

@WebServlet("/")
public class ParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ParticipantDao participantDao;
   

	
	public void init(){
		participantDao = new ParticipantDao();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String action = request.getServletPath();
		
		
		switch(action) {
		case "/new":
			try {
				showNewForm(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "/insert":
			try {
				insertParticipant(request, response);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteParticipant(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/edit":
			try {
			showEditForm(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/selectbatch":
			try {
				showBatchForm(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/update":
			try {
				updateParticipant(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "/select":
			try {
				selectParticipantB(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			default:
			try {
				listParticipant(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							break;
			}

		
	}
	private void listParticipant(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	List<Participant> listParticipant = participantDao.selectAllParticipants();
	request.setAttribute("listParticipants", listParticipant);
	RequestDispatcher dispatcher = request.getRequestDispatcher("participant-list.jsp");
	dispatcher.forward(request, response);
	}
	
	private void selectParticipantB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String batch = request.getParameter("batch");

	   try { 
	 
	    List<Participant> listParticipantb = participantDao.selectAllParticipantB(batch);
	    request.setAttribute("listParticipantsb", listParticipantb);
		RequestDispatcher dispatcher = request.getRequestDispatcher("batchpar-list.jsp");
		dispatcher.forward(request, response);
	   }catch (Exception e)  {
		   e.printStackTrace();
	   }
		
		}
	
	private void showBatchForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BatchPar.jsp");
		dispatcher.forward(request, response);
	
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("par-form.jsp");
		dispatcher.forward(request, response);
	
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		Participant existingParticipant;
		try {
		existingParticipant = participantDao.selectParticipant(cid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("par-form.jsp");
		request.setAttribute("participant", existingParticipant);
		dispatcher.forward(request, response);
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	private void insertParticipant(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String batch = request.getParameter("batch");
		String pcode = request.getParameter("pcode");
		
		Participant participant = new Participant();
		participant.setFirstname(firstname);
		participant.setLastname(lastname);
		participant.setEmail(email);
		participant.setPhone(phone);
		participant.setBatch(batch);
	
		
	
			participantDao.insertParticipant(participant);
	
		response.sendRedirect("list");
	}
	
	private void updateParticipant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String batch = request.getParameter("batch");


		
		Participant participant = new Participant();
		participant.setCid(cid);
		participant.setFirstname(firstname);
		participant.setLastname(lastname);
		participant.setEmail(email);
		participant.setPhone(phone);
		participant.setBatch(batch);
		
		
		int result=participantDao.updateParticipant(participant);
		if( result>0) {
					
		response.sendRedirect("list");
	}else {
		System.out.println("update failed");
	}
	}

	private void deleteParticipant(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		try {
		participantDao.deleteParticipant(cid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");

	}


}
	
	
	
