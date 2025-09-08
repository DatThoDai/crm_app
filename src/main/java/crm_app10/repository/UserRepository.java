package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Users;
import entity.Role;

public class UserRepository {
	/*
	 * cach dat ten ham trong repo de goi nho toi cau truy van
	 * select * from users u  findByEmailAndPassword
	   where u.email = 'nguyenvana@gmail.com' and u.password = '123456' ;
	 */
	
	public List<Users> findAll() {
		List<Users> listUsers = new ArrayList<Users>();
		
		String query = "select * from users u join roles r ON u.role_id = r.id";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Users user = new Users();
				user.setFullName(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setId(resultSet.getInt("id"));
				
				// Tạo đối tượng Role
				Role role = new Role();
				role.setId(resultSet.getInt("role_id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				user.setRole(role);
				
				listUsers.add(user);
			}
		} catch (Exception e) {
			System.out.println("findAll User error: " + e.getMessage());
		}
		return listUsers;
	}
	
	public int deleteById(int id) {
		int rowCount = 0;
		String query = "DELETE FROM users u WHERE u.id = ? ";
		Connection connection = MySQLConfig.getConnection(); 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return rowCount;
	}
	
	public boolean addUser(String email, String password, String fullName, int roleId) {
		String query = "INSERT INTO users (email, password, fullname, role_id) VALUES (?, ?, ?, ?)";
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, fullName);
			statement.setInt(4, roleId);
			
			int result = statement.executeUpdate();
			return result > 0;
			
		} catch (Exception e) {
			System.out.println("addUser error: " + e.getMessage());
			return false;
		}
	}
	public Users findUserById(int id) {
	    String query = "SELECT * FROM users WHERE id = ?";
	    Connection connection = MySQLConfig.getConnection();
	    
	    try {
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            Users user = new Users();
	            user.setId(resultSet.getInt("id"));
	            user.setEmail(resultSet.getString("email"));
	            user.setPassword(resultSet.getString("password"));
	            user.setFullName(resultSet.getString("fullname"));
	            user.setRoleId(resultSet.getInt("role_id"));
	            return user;
	        }
	    } catch (Exception e) {
	        System.out.println("findUserById error: " + e.getMessage());
	    }
	    
	    return null;
	}
	
	public Users findUserByEmail(String email) {
	    String query = "SELECT * FROM users WHERE email = ?";
	    Connection connection = MySQLConfig.getConnection();
	    
	    try {
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, email);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            Users user = new Users();
	            user.setId(resultSet.getInt("id"));
	            user.setEmail(resultSet.getString("email"));
	            user.setPassword(resultSet.getString("password"));
	            user.setFullName(resultSet.getString("fullname"));
	            user.setRoleId(resultSet.getInt("role_id"));
	            return user;
	        }
	    } catch (Exception e) {
	        System.out.println("findUserByEmail error: " + e.getMessage());
	    }
	    
	    return null;
	}
	
	public boolean updateUser(int id, String email, String password, String fullName, int roleId) {
	    String query = "UPDATE users SET email = ?, password = ?, fullname = ?, role_id = ? WHERE id = ?";
	    Connection connection = MySQLConfig.getConnection();
	    
	    try {
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, email);
	        statement.setString(2, password);
	        statement.setString(3, fullName);
	        statement.setInt(4, roleId);
	        statement.setInt(5, id);
	        
	        int result = statement.executeUpdate();
	        return result > 0;
	        
	    } catch (Exception e) {
	        System.out.println("updateUser error: " + e.getMessage());
	        return false;
	    }
	}
	
}
