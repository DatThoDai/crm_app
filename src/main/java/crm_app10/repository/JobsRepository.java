package crm_app10.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
