package crm_app10.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Jobs;

public class JobsRepository {
	public List<Jobs> findAllJobs() {
		List<Jobs> listJobs = new ArrayList<Jobs>();
		String query = "select * from jobs";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Jobs job = new Jobs();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("name"));
				job.setStartDate(resultSet.getDate("start_date").toLocalDate());
				job.setEndDate(resultSet.getDate("end_date").toLocalDate());
				listJobs.add(job);
			}
		} catch (Exception e) {
			System.out.println("findAllJobs error: " + e.getMessage());
		}
		
		return listJobs;
	}
//	public boolean addJob(String name,String startDate,String endDate) {
//		String query = "INSERT INTO jobs (name, start_date, end_date) VALUES (?, ?, ?)";
//		Connection connection = MySQLConfig.getConnection();
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, name);
//			statement.setDate(2, Date.valueOf(LocalDate.parse(startDate)));
//			statement.setDate(3, Date.valueOf(LocalDate.parse(endDate)));
//
//			int result = statement.executeUpdate();
//			return result > 0;
//		} catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//			return false;
//		}
//	}
	
	public boolean addJob(String name, String startDate, String endDate) {
	    String query = "INSERT INTO jobs (name, start_date, end_date) VALUES (?, ?, ?)";
	    Connection connection = MySQLConfig.getConnection();
	    
	    try {
	        // Debug log
	        System.out.println("=== DEBUG ADD JOB ===");
	        System.out.println("Name: " + name);
	        System.out.println("Start Date: " + startDate);
	        System.out.println("End Date: " + endDate);
	        System.out.println("Connection: " + (connection != null ? "OK" : "NULL"));
	        
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, name);
	        statement.setDate(2, Date.valueOf(LocalDate.parse(startDate)));
	        statement.setDate(3, Date.valueOf(LocalDate.parse(endDate)));

	        System.out.println("Query: " + query);
	        System.out.println("About to execute...");
	        
	        int result = statement.executeUpdate();
	        
	        System.out.println("Result: " + result);
	        System.out.println("=== END DEBUG ===");
	        
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println("AddJob Error: " + e.getMessage());
	        e.printStackTrace(); // ✅ Thêm stack trace để xem lỗi chi tiết
	        return false;
	    }
	}
}
