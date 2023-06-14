package com.zumbamanagement.web;

import jakarta.servlet.ServletConfig;
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

import com.zumbamangement.dao.BatchDao;

@WebServlet("/BServlet")
public class BatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private BatchDao batchDao;  

	
	public void init() throws ServletException {
		batchDao = new BatchDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		
		if (action != null) {
            switch (action) {
                case "newb":
                    showNewFormB(request, response);
                    break;

                case "insertb":
                    try {
                        try {
							insertBatch(request, response);
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "deleteb":
                    try {
                        deleteBatch(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "editb":
                    try {
                        showEditFormB(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "updateb":
                    try {
                        updateBatch(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
				try {
					listBatch(request, response);
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
            }
        } else {
            try {
				listBatch(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
						
		

	
	
	
		
		private void showNewFormB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("batch-form.jsp");
			dispatcher.forward(request, response);
	}
		
		private void insertBatch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
			String option = request.getParameter("option");
			String days = request.getParameter("days");
			String time = request.getParameter("time");
			
			Batch batch = new Batch();
			batch.setOption(option);
			batch.setDays(days);
			batch.setTime(time);
			
			batchDao.insertBatch(batch);
			response.sendRedirect("BServlet?action=listb");
		}
	
		private void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				batchDao.deleteBatch(id);
			}catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("BServlet?action=listb");
		}
		
		private void showEditFormB (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{ 
			int id = Integer.parseInt(request.getParameter("id"));
			
			Batch existingBatch;
			try {
				existingBatch = batchDao.selectBatch(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("batch-form.jsp");
				request.setAttribute("batch", existingBatch);
				dispatcher.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		
		private void updateBatch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String option = request.getParameter("option");
			String days = request.getParameter("days");
			String time = request.getParameter("time");
	
			
			Batch batch = new Batch();
			batch.setId(id);
			batch.setOption(option);
			batch.setDays(days);
			batch.setTime(time);
			
			
			int result=batchDao.updateBatch(batch);
			if( result>0) {
						
			response.sendRedirect("BServlet?action=listb");
		}else {
			System.out.println("update failed");
		}
		}
		
		
		private void listBatch(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			List<Batch> listBatch = batchDao.selectAllBatches();
			request.setAttribute("listBatches", listBatch);
			RequestDispatcher dispatcher = request.getRequestDispatcher("batch-list.jsp");
			dispatcher.forward(request, response);
		}
		
	}


	

