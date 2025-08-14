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
}
