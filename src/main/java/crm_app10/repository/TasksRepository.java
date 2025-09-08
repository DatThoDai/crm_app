package crm_app10.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.MySQLConfig;
import entity.Tasks;
import entity.Users;
import entity.Jobs;
import entity.Status;

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
				
				Users user = new Users();
				user.setId(resultSet.getInt("user_id"));
				user.setFullName(resultSet.getString("user_name"));
				task.setUser(user);
				
				Jobs job = new Jobs();
				job.setId(resultSet.getInt("job_id"));
				job.setName(resultSet.getString("job_name"));
				task.setJob(job);
				
				Status status = new Status();
				status.setId(resultSet.getInt("status_id"));
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				
				listTasks.add(task);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return listTasks;
	}

	public boolean addTask(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
		String query = "INSERT INTO tasks(name, start_date, end_date, user_id, job_id, status_id) VALUES(?, ?, ?, ?, ?, ?)";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, Date.valueOf(startDate));
			statement.setDate(3, Date.valueOf(endDate));
			statement.setInt(4, userId);
			statement.setInt(5, jobId);
			statement.setInt(6, statusId);
			
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

	public boolean updateTask(int id, String name, String startDate, String endDate, int userId, int jobId, int statusId) {
		String query = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, user_id = ?, job_id = ?, status_id = ? WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, Date.valueOf(startDate));
			statement.setDate(3, Date.valueOf(endDate));
			statement.setInt(4, userId);
			statement.setInt(5, jobId);
			statement.setInt(6, statusId);
			statement.setInt(7, id);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}
	
	public Map<String, Integer> getTaskStatistics() {
		Map<String, Integer> stats = new HashMap<>();
		String query = """
			SELECT 
				COUNT(*) as total,
				SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) as pending,
				SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) as inProgress,
				SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) as completed
			FROM tasks
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				stats.put("total", resultSet.getInt("total"));
				stats.put("pending", resultSet.getInt("pending"));
				stats.put("inProgress", resultSet.getInt("inProgress"));
				stats.put("completed", resultSet.getInt("completed"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			stats.put("total", 0);
			stats.put("pending", 0);
			stats.put("inProgress", 0);
			stats.put("completed", 0);
		}
		
		return stats;
	}
	
	public List<Tasks> findTasksByUserId(int userId) {
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
			WHERE t.user_id = ?
			ORDER BY t.start_date DESC
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
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
				
				Users user = new Users();
				user.setId(resultSet.getInt("user_id"));
				user.setFullName(resultSet.getString("user_name"));
				task.setUser(user);
				
				Jobs job = new Jobs();
				job.setId(resultSet.getInt("job_id"));
				job.setName(resultSet.getString("job_name"));
				task.setJob(job);
				
				Status status = new Status();
				status.setId(resultSet.getInt("status_id"));
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				
				listTasks.add(task);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return listTasks;
	}
	
	public Map<String, Integer> getUserTaskStatistics(int userId) {
		Map<String, Integer> stats = new HashMap<>();
		String query = """
			SELECT 
				COUNT(*) as total,
				SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) as pending,
				SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) as inProgress,
				SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) as completed
			FROM tasks
			WHERE user_id = ?
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				stats.put("total", resultSet.getInt("total"));
				stats.put("pending", resultSet.getInt("pending"));
				stats.put("inProgress", resultSet.getInt("inProgress"));
				stats.put("completed", resultSet.getInt("completed"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			stats.put("total", 0);
			stats.put("pending", 0);
			stats.put("inProgress", 0);
			stats.put("completed", 0);
		}
		
		return stats;
	}
	
	public List<Tasks> findTasksByJobId(int jobId) {
		String query = """
			SELECT t.*, j.name as jobName, u.fullname as userName, s.name as statusName 
			FROM tasks t 
			LEFT JOIN jobs j ON t.job_id = j.id 
			LEFT JOIN users u ON t.user_id = u.id 
			LEFT JOIN status s ON t.status_id = s.id 
			WHERE t.job_id = ?
		""";
		
		List<Tasks> tasksList = new ArrayList<>();
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, jobId);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Tasks task = new Tasks();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getDate("start_date") != null ? 
						resultSet.getDate("start_date").toLocalDate() : null);
				task.setEndDate(resultSet.getDate("end_date") != null ? 
						resultSet.getDate("end_date").toLocalDate() : null);
				task.setUserId(resultSet.getInt("user_id"));
				task.setJobId(resultSet.getInt("job_id"));
				task.setStatusId(resultSet.getInt("status_id"));
				
				Jobs job = new Jobs();
				job.setId(resultSet.getInt("job_id"));
				job.setName(resultSet.getString("jobName"));
				task.setJob(job);
				
				Users user = new Users();
				user.setId(resultSet.getInt("user_id"));
				user.setFullName(resultSet.getString("userName"));
				task.setUser(user);
				
				Status status = new Status();
				status.setId(resultSet.getInt("status_id"));
				status.setName(resultSet.getString("statusName"));
				task.setStatus(status);
				
				tasksList.add(task);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return tasksList;
	}
	
	public Map<String, Integer> getJobTaskStatistics(int jobId) {
		Map<String, Integer> stats = new HashMap<>();
		String query = """
			SELECT 
				COUNT(*) as total,
				SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) as pending,
				SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) as inProgress,
				SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) as completed
			FROM tasks WHERE job_id = ?
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, jobId);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				stats.put("total", resultSet.getInt("total"));
				stats.put("pending", resultSet.getInt("pending"));
				stats.put("inProgress", resultSet.getInt("inProgress"));
				stats.put("completed", resultSet.getInt("completed"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			stats.put("total", 0);
			stats.put("pending", 0);
			stats.put("inProgress", 0);
			stats.put("completed", 0);
		}
		
		return stats;
	}
}
