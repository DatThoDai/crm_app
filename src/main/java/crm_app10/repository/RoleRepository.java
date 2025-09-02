package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;

public class RoleRepository {
	// lay danh sach role
	public List<Role> findAllRole() {
		List<Role> listRole = new ArrayList<Role>();

		String query = "select * from roles";

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				listRole.add(role);	
			}
		} catch (Exception e) {
			System.out.println("findAllRole error: " + e.getMessage());
		}
		return listRole;
	}
	
	public int addRole(String name, String desc) {
		int rowCount = 0;
		String query = "INSERT INTO roles (name, description) VALUES (?, ?)";
		Connection connection = MySQLConfig.getConnection(); 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, desc);
			
			rowCount = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return rowCount;
	}
	
	public boolean deleteRole(int id) {
		String query = "DELETE FROM roles WHERE id = ? ";
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
	
	public Role findById(int id) {
		String query = "SELECT * FROM roles WHERE id = ? ";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				return role;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}
	
	public boolean updateRole(int id, String roleName, String description) {
		String query = "UPDATE roles SET name = ?, description = ? WHERE id = ? ";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, roleName);
			statement.setString(2, description);
			statement.setInt(3, id);

			int result = statement.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
	}
	
}
