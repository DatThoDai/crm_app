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
			WHERE t.id = ?
		""";
		
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
	
	public boolean updateTaskStatus(int id, int statusId) {
		String query = "UPDATE tasks SET status_id = ? WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, statusId);
			statement.setInt(2, id);
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error updating task status: " + e.getMessage());
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
	
	// 1. Dự án cần ưu tiên - Gần deadline và còn nhiều task chưa hoàn thành
	public List<Map<String, Object>> getUrgentProjects(int limit) {
		List<Map<String, Object>> results = new ArrayList<>();
		String query = """
			SELECT 
				j.id, 
				j.name,
				j.end_date,
				COUNT(t.id) as total_tasks,
				SUM(CASE WHEN t.status_id != 3 THEN 1 ELSE 0 END) as incomplete_tasks,
				DATEDIFF(j.end_date, CURDATE()) as days_remaining
			FROM jobs j
			LEFT JOIN tasks t ON j.id = t.job_id
			WHERE j.end_date >= CURDATE()
			GROUP BY j.id, j.name, j.end_date
			HAVING incomplete_tasks > 0
			ORDER BY days_remaining ASC, incomplete_tasks DESC
			LIMIT ?
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Map<String, Object> project = new HashMap<>();
				project.put("id", resultSet.getInt("id"));
				project.put("name", resultSet.getString("name"));
				project.put("endDate", resultSet.getDate("end_date"));
				project.put("totalTasks", resultSet.getInt("total_tasks"));
				project.put("incompleteTasks", resultSet.getInt("incomplete_tasks"));
				project.put("daysRemaining", resultSet.getInt("days_remaining"));
				results.add(project);
			}
		} catch (SQLException e) {
			System.out.println("Error getting urgent projects: " + e.getMessage());
		}
		
		return results;
	}
	
	// 2. Task sắp hết hạn - Deadline trong 7 ngày tới và chưa hoàn thành
	public List<Map<String, Object>> getUpcomingTasks(int limit) {
		List<Map<String, Object>> results = new ArrayList<>();
		String query = """
			SELECT 
				t.id,
				t.name,
				t.end_date,
				t.status_id,
				u.fullname as user_name,
				j.name as job_name,
				s.name as status_name,
				DATEDIFF(t.end_date, CURDATE()) as days_remaining
			FROM tasks t
			JOIN users u ON t.user_id = u.id
			JOIN jobs j ON t.job_id = j.id
			JOIN status s ON t.status_id = s.id
			WHERE t.status_id != 3 
			  AND t.end_date >= CURDATE()
			  AND t.end_date <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)
			ORDER BY t.end_date ASC
			LIMIT ?
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Map<String, Object> task = new HashMap<>();
				task.put("id", resultSet.getInt("id"));
				task.put("name", resultSet.getString("name"));
				task.put("endDate", resultSet.getDate("end_date"));
				task.put("userName", resultSet.getString("user_name"));
				task.put("jobName", resultSet.getString("job_name"));
				task.put("statusName", resultSet.getString("status_name"));
				task.put("daysRemaining", resultSet.getInt("days_remaining"));
				results.add(task);
			}
		} catch (SQLException e) {
			System.out.println("Error getting upcoming tasks: " + e.getMessage());
		}
		
		return results;
	}
	
	// 3. Nhân viên quá tải - Có nhiều task đang thực hiện
	public List<Map<String, Object>> getOverloadedUsers(int limit) {
		List<Map<String, Object>> results = new ArrayList<>();
		String query = """
			SELECT 
				u.id,
				u.fullname,
				u.email,
				COUNT(CASE WHEN t.status_id = 2 THEN 1 END) as in_progress_tasks,
				COUNT(CASE WHEN t.status_id = 1 THEN 1 END) as pending_tasks,
				COUNT(t.id) as total_assigned
			FROM users u
			LEFT JOIN tasks t ON u.id = t.user_id AND t.status_id IN (1, 2)
			GROUP BY u.id, u.fullname, u.email
			HAVING in_progress_tasks > 0
			ORDER BY in_progress_tasks DESC, pending_tasks DESC
			LIMIT ?
		""";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Map<String, Object> user = new HashMap<>();
				user.put("id", resultSet.getInt("id"));
				user.put("fullname", resultSet.getString("fullname"));
				user.put("email", resultSet.getString("email"));
				user.put("inProgressTasks", resultSet.getInt("in_progress_tasks"));
				user.put("pendingTasks", resultSet.getInt("pending_tasks"));
				user.put("totalAssigned", resultSet.getInt("total_assigned"));
				results.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Error getting overloaded users: " + e.getMessage());
		}
		
		return results;
	}
}
