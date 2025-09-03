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
	public boolean addJob(String name,String startDate,String endDate) {
		String query = "INSERT INTO jobs (name, start_date, end_date) VALUES (?, ?, ?)";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, Date.valueOf(LocalDate.parse(startDate)));
			statement.setDate(3, Date.valueOf(LocalDate.parse(endDate)));

			int result = statement.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
	}
	
	public boolean deleteJob(int id) {
		String query = "DELETE FROM jobs WHERE id = ? ";
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			int result = statement.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
	}
	
	public Jobs findJobById(int id) {
		Jobs job = null;
		String query = "SELECT * FROM jobs WHERE id = ? ";
		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				job = new Jobs();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("name"));
				job.setStartDate(resultSet.getDate("start_date").toLocalDate());
				job.setEndDate(resultSet.getDate("end_date").toLocalDate());
			}
		} catch (Exception e) {
			System.out.println("findJobById error: " + e.getMessage());
		}

		return job;
	}
	
	public boolean updateJob(int id, String name, String startDate, String endDate) {
		String query = "UPDATE jobs SET name = ?, start_date = ?, end_date = ? WHERE id = ?";
		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, Date.valueOf(LocalDate.parse(startDate)));
			statement.setDate(3, Date.valueOf(LocalDate.parse(endDate)));
			statement.setInt(4, id);

			int result = statement.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("updateJob error: " + e.getMessage());
			return false;
		}
	}
}
