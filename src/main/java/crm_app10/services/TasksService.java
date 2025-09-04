package crm_app10.services;

import java.util.List;

import crm_app10.repository.TasksRepository;
import entity.Tasks;

public class TasksService {
	private TasksRepository tasksRepository = new TasksRepository();

	public List<Tasks> findAllTasks() {
		return tasksRepository.findAllTasks();
	}

	public boolean addTask(String name, String startDate, String endDate, int userId, int jobId) {
		return tasksRepository.addTask(name, startDate, endDate, userId, jobId);
	}

	public boolean deleteTask(int id) {
		return tasksRepository.deleteTask(id);
	}

	public Tasks findTaskById(int id) {
		return tasksRepository.findTaskById(id);
	}

	public boolean updateTask(int id, String name, String startDate, String endDate, int userId, int jobId) {
		return tasksRepository.updateTask(id, name, startDate, endDate, userId, jobId);
	}
}
