package crm_app10.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Tasks;

public class TasksRepository {
	
	public List<Tasks> findAllTasks() {
		List<Tasks> listTasks = new ArrayList<>();
		String query = """
			SELECT t.id, t.name, t.start_date, t.end_date, 
			       t.user_id, t.job_id, t.status_id,
			       u.fullname as user_name, 
			       j.name as job_name, 
			       s.name as status_name
			FROM tasks t
			JOIN users u ON t.user_id = u.id
			JOIN jobs j ON t.job_id = j.id
			JOIN status s ON t.status_id = s.id
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Tasks task = new Tasks();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getDate("start_date").toLocalDate());
				task.setEndDate(resultSet.getDate("end_date").toLocalDate());
				task.setUserId(resultSet.getInt("user_id"));
				task.setJobId(resultSet.getInt("job_id"));
				task.setStatusId(resultSet.getInt("status_id"));
				task.setUserName(resultSet.getString("user_name"));
				task.setJobName(resultSet.getString("job_name"));
				task.setStatusName(resultSet.getString("status_name"));
				listTasks.add(task);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return listTasks;
	}

	public boolean addTask(String name, String startDate, String endDate, int userId, int jobId) {
		String query = "INSERT INTO tasks(name, start_date, end_date, user_id, job_id, status_id) VALUES(?, ?, ?, ?, ?, 1)";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, Date.valueOf(startDate));
			statement.setDate(3, Date.valueOf(endDate));
			statement.setInt(4, userId);
			statement.setInt(5, jobId);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}

	public boolean deleteTask(int id) {
		String query = "DELETE FROM tasks WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}

	public Tasks findTaskById(int id) {
		String query = "SELECT * FROM tasks WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				Tasks task = new Tasks();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getDate("start_date").toLocalDate());
				task.setEndDate(resultSet.getDate("end_date").toLocalDate());
				task.setUserId(resultSet.getInt("user_id"));
				task.setJobId(resultSet.getInt("job_id"));
				task.setStatusId(resultSet.getInt("status_id"));
				return task;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}

	public boolean updateTask(int id, String name, String startDate, String endDate, int userId, int jobId) {
		String query = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, user_id = ?, job_id = ? WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, Date.valueOf(startDate));
			statement.setDate(3, Date.valueOf(endDate));
			statement.setInt(4, userId);
			statement.setInt(5, jobId);
			statement.setInt(6, id);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}
}
