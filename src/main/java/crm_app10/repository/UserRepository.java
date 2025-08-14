package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Users;

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
				user.setRoleDescription(resultSet.getString("description"));
				listUsers.add(user);
			}
		} catch (Exception e) {
			System.out.println("findAll User error: " + e.getMessage());
		}
		return listUsers;
	}
	
}
