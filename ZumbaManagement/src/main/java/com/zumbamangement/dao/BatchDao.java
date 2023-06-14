package com.zumbamangement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zumbamanagement.model.Batch;

import java.util.ArrayList;
import java.sql.Statement;

public class BatchDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/zumba";
	private String jdbcUsername = "root";
	private String jdbcPassword = "6781jjF!";
	Statement statement;
	Connection connection;
	PreparedStatement preparedStatement;
	
	
	private static final String INSERT_BATCH_SQL = "insert into batch values(null, ?, ?, ?)";
	private static final String SELECT_BATCH_BY_ID = " select * from batch where id = ?";
	private static final String SELECT_ALL_BATCHES = "select * from batch;";
	private static final String DELETE_BATCH_SQL = "delete from batch where id=?;";
	private static final String UPDATE_BATCH_SQL = "update batch set `option`=?, days= ?, time= ? where id= ?;";
	
	public BatchDao() {
		
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
	
	
	public int insertBatch(Batch batch) throws SQLException {
		System.out.println(INSERT_BATCH_SQL);
		try (Connection connection = getConnection ();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BATCH_SQL)){
			preparedStatement.setString(1, batch.getOption());
			preparedStatement.setString(2, batch.getDays());
			preparedStatement.setString(3, batch.getTime());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
						
	}
	
	public Batch selectBatch(int id) {
		Batch batch = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BATCH_BY_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String option = rs.getString("option");
				String days = rs.getString("days");
				String time = rs.getString("time");
				return new Batch(id, option, days, time); }
				
		}catch (SQLException e) {
			printSQLException(e);
		}
		return batch;
	}
	
	public List<Batch> selectAllBatches(){
		List<Batch> batches = new ArrayList<>();
		try (Connection connection = getConnection ();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BATCHES);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String option = rs.getString("option");
				String days = rs.getString("days");
				String time = rs.getString("time");
				int id = rs.getInt("id");
				batches.add(new Batch(option, days, time, id));
			}
		}catch (SQLException e) {
			printSQLException(e);
		}
		return batches;
	}
	


	public int updateBatch(Batch batch) throws SQLException {
		int result=0;
		try (Connection connection = getConnection ();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BATCH_SQL)){
			preparedStatement.setString(1, batch.getOption());
			preparedStatement.setString(2, batch.getDays());
			preparedStatement.setString(3, batch.getTime());
			preparedStatement.setInt(4,  batch.getId());
			System.out.println(preparedStatement);
			
			result= preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
						

	}
	
	public boolean deleteBatch (int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BATCH_SQL);) {
					statement.setInt(1, id);
					rowDeleted = statement.executeUpdate() >0;
				}
				return rowDeleted;
	}
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e). getSQLState());
				System.err.println("Error code: "+ ((SQLException) e).getErrorCode());
				System.err.println("message: "+ e.getMessage());
				Throwable t = ex.getCause();
				while (t !=null) {
					System.out.println("Cause: "+t);
					t=t.getCause();
				}
			}
		}
	}
	
	
	
	
}
