package com.zumbamangement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;

import com.zumbamanagement.model.Batch;
import com.zumbamanagement.model.Participant;

public class ParticipantDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/zumba";
	private String jdbcUsername = "root";
	private String jdbcPassword = "6781jjF!";
	Statement statement;
	Connection connection;
	PreparedStatement preparedStatement;
	
	
	private static final String INSERT_PARTICIPANT_SQL = "insert into participant values(null, ?, ?, ?, ?, ?)";
	private static final String SELECT_PARTICIPANT_BY_ID = " select * from participant where cid = ?";
	private static final String SELECT_PARTICIPANTS_BY_BATCH = " select * from participant where batch = ?;";
	private static final String SELECT_ALL_PARTICIPANTS = "select * from participant;";
	private static final String DELETE_PARTICIPANT_SQL = "delete from participant where cid=?;";
	private static final String UPDATE_PARTICIPANT_SQL = "update participant set firstname= ?, lastname= ?, email= ?, phone= ?, batch= ? where cid= ?;";
	
	public ParticipantDao() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	int result = 0;
	
	public int insertParticipant (Participant participant)throws SQLException {
		System.out.println(INSERT_PARTICIPANT_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PARTICIPANT_SQL)){
			preparedStatement.setString(1, participant.getFirstname());
			preparedStatement.setString(2, participant.getLastname());
			preparedStatement.setString(3, participant.getEmail());
			preparedStatement.setString(4, participant.getPhone());
			preparedStatement.setString(5, participant.getBatch());
			
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}


//	
	
	public Participant selectParticipant(int cid) {
		Participant participant = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARTICIPANT_BY_ID);){
			preparedStatement.setInt(1, cid);
			System.out.println(preparedStatement);			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
		
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String batch = rs.getString("batch");
				
				
				return new Participant(cid, firstname, lastname, email, phone, batch);
				
		}
		}catch (SQLException e) {
			printSQLException(e);
		}
		return participant;
	}

	public List<Participant> selectAllParticipantB(String batch){
		List<Participant> participants = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARTICIPANTS_BY_BATCH);){
			preparedStatement.setString(1, batch);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				int cid = rs.getInt("cid");
				participants.add(new Participant(firstname, lastname, email, phone, cid));
			}
		}catch (SQLException e) {
			printSQLException(e);
		}
		return participants;
		
	}
	public List<Participant> selectAllParticipants(){
		List<Participant> participants = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PARTICIPANTS);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String batch = rs.getString("batch");
				int cid = rs.getInt("cid");
				participants.add(new Participant(firstname, lastname, email, phone, batch, cid));
			}
		}catch (SQLException e) {
			printSQLException(e);
		}
		return participants;
	}
	
		public int updateParticipant(Participant participant) throws SQLException {
			int result=0;
			try (Connection connection = getConnection ();
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PARTICIPANT_SQL)){
				preparedStatement.setString(1, participant.getFirstname());
				preparedStatement.setString(2, participant.getLastname());
				preparedStatement.setString(3, participant.getEmail());
				preparedStatement.setString(4, participant.getPhone());
				preparedStatement.setString(5, participant.getBatch());
				preparedStatement.setInt(6,  participant.getCid());
				System.out.println(preparedStatement);
				
				result= preparedStatement.executeUpdate();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
							

		}

	
	
	public boolean deleteParticipant(int cid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PARTICIPANT_SQL);) {
			statement.setInt(1, cid);
			rowDeleted = statement.executeUpdate() >0;
		}
		return rowDeleted;
	}
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "+ ((SQLException) e).getErrorCode());
				System.err.println("Message: "+e.getMessage());
				Throwable t = ex.getCause();
				while (t !=null) {
					System.out.println("cause: "+t);
					t=t.getCause();
				}
			}
		}
		
	}


	
	
	
	
}
