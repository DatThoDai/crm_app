package crm_app10.services;

import java.util.List;
import java.util.Map;

import crm_app10.repository.TasksRepository;
import entity.Tasks;

public class TasksService {
	private TasksRepository tasksRepository = new TasksRepository();

	public List<Tasks> findAllTasks() {
		return tasksRepository.findAllTasks();
	}

	public boolean addTask(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
		return tasksRepository.addTask(name, startDate, endDate, userId, jobId, statusId);
	}

	public boolean deleteTask(int id) {
		return tasksRepository.deleteTask(id);
	}

	public Tasks findTaskById(int id) {
		return tasksRepository.findTaskById(id);
	}

	public boolean updateTask(int id, String name, String startDate, String endDate, int userId, int jobId, int statusId) {
		return tasksRepository.updateTask(id, name, startDate, endDate, userId, jobId, statusId);
	}
	
	public Map<String, Integer> getTaskStatistics() {
		return tasksRepository.getTaskStatistics();
	}
	
	public List<Tasks> findTasksByUserId(int userId) {
		return tasksRepository.findTasksByUserId(userId);
	}
	
	public Map<String, Integer> getUserTaskStatistics(int userId) {
		return tasksRepository.getUserTaskStatistics(userId);
	}
	
	public List<Tasks> findTasksByJobId(int jobId) {
		return tasksRepository.findTasksByJobId(jobId);
	}
	
	public Map<String, Integer> getJobTaskStatistics(int jobId) {
		return tasksRepository.getJobTaskStatistics(jobId);
	}
}
