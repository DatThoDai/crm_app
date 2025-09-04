package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import crm_app10.services.JobsService;
import crm_app10.services.StatusService;
import crm_app10.services.TasksService;
import crm_app10.services.UserService;
import entity.Jobs;
import entity.Status;
import entity.Tasks;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TasksController", urlPatterns = { "/tasks", "/task-add", "/task-delete", "/task-edit" })
public class TasksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TasksService tasksService = new TasksService();
	private UserService userService = new UserService();
	private JobsService jobsService = new JobsService();
	private StatusService statusService = new StatusService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/tasks")) {
			List<Tasks> listTasks = tasksService.findAllTasks();
			req.setAttribute("listTasks", listTasks);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
		} else if (path.equals("/task-add")) {
			List<Users> listUsers = userService.findAll();
			List<Jobs> listJobs = jobsService.findAllJobs();
			List<Status> listStatus = statusService.findAllStatus();
			req.setAttribute("listUsers", listUsers);
			req.setAttribute("listJobs", listJobs);
			req.setAttribute("listStatus", listStatus);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		} else if (path.equals("/task-delete")) {
			String idTask = req.getParameter("id");
			if (idTask != null) {
				boolean isSuccess = tasksService.deleteTask(Integer.parseInt(idTask));
				if (isSuccess) {
					resp.sendRedirect("tasks?msg=deleteSuccess");
				} else {
					resp.sendRedirect("tasks?msg=deleteError");
				}
			}
		} else if (path.equals("/task-edit")) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				Tasks task = tasksService.findTaskById(Integer.parseInt(idParam));
				List<Users> listUsers = userService.findAll();
				List<Jobs> listJobs = jobsService.findAllJobs();
				List<Status> listStatus = statusService.findAllStatus();
				req.setAttribute("task", task);
				req.setAttribute("listUsers", listUsers);
				req.setAttribute("listJobs", listJobs);
				req.setAttribute("listStatus", listStatus);
				req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/task-add")) {
			String taskName = req.getParameter("taskName");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			String userId = req.getParameter("userId");
			String jobId = req.getParameter("jobId");
			String statusId = req.getParameter("statusId");
			
			// Nếu không chọn status, mặc định là 1 (Chưa bắt đầu)
			int statusIdInt = (statusId != null && !statusId.isEmpty()) ? Integer.parseInt(statusId) : 1;
			
			boolean isSuccess = tasksService.addTask(taskName, startDate, endDate, 
				Integer.parseInt(userId), Integer.parseInt(jobId), statusIdInt);
			if (isSuccess) {
				resp.sendRedirect("tasks?msg=addSuccess");
			} else {
				resp.sendRedirect("tasks?msg=addError");
			}
		} else if (path.equals("/task-edit")) {
			String id = req.getParameter("id");
			String taskName = req.getParameter("taskName");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			String userId = req.getParameter("userId");
			String jobId = req.getParameter("jobId");
			String statusId = req.getParameter("statusId");
			
			boolean isSuccess = tasksService.updateTask(Integer.parseInt(id), taskName, startDate, endDate, 
				Integer.parseInt(userId), Integer.parseInt(jobId), Integer.parseInt(statusId));
			if (isSuccess) {
				resp.sendRedirect("tasks?msg=updateSuccess");
			} else {
				resp.sendRedirect("tasks?msg=updateError");
			}
		}
	}
}
