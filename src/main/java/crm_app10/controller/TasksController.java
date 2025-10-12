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
import jakarta.servlet.http.Cookie;
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
				String userRole = getUserRoleFromCookie(req);
				String userId = getUserIdFromCookie(req);
				if (userId == null) {
					userId = getUserIdFromEmail(req);
				}
				
				Tasks task = tasksService.findTaskById(Integer.parseInt(idParam));
				
				if ("ROLE_ADMIN".equals(userRole) || "ROLE_LEADER".equals(userRole)) {
					List<Users> listUsers = userService.findAll();
					List<Jobs> listJobs = jobsService.findAllJobs();
					List<Status> listStatus = statusService.findAllStatus();
					req.setAttribute("task", task);
					req.setAttribute("listUsers", listUsers);
					req.setAttribute("listJobs", listJobs);
					req.setAttribute("listStatus", listStatus);
					req.setAttribute("userRole", userRole);
					req.getRequestDispatcher("task-add.jsp").forward(req, resp);
				} else if ("ROLE_MEMBER".equals(userRole)) {
					if (userId != null && task != null && task.getUserId() == Integer.parseInt(userId)) {
						List<Status> listStatus = statusService.findAllStatus();
						req.setAttribute("task", task);
						req.setAttribute("listStatus", listStatus);
						req.setAttribute("userRole", userRole);
						req.getRequestDispatcher("task-add.jsp").forward(req, resp);
					} else {
						resp.sendRedirect("404.html");
					}
				} else {
					resp.sendRedirect("404.html");
				}
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
			String userRole = getUserRoleFromCookie(req);
			String currentUserId = getUserIdFromCookie(req);
			if (currentUserId == null) {
				currentUserId = getUserIdFromEmail(req);
			}
			
			if ("ROLE_ADMIN".equals(userRole) || "ROLE_LEADER".equals(userRole)) {
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
			} else if ("ROLE_MEMBER".equals(userRole)) {
				String statusId = req.getParameter("statusId");
				
				if (currentUserId != null && statusId != null) {
					Tasks currentTask = tasksService.findTaskById(Integer.parseInt(id));
					if (currentTask != null && currentTask.getUserId() == Integer.parseInt(currentUserId)) {
						boolean isSuccess = tasksService.updateTaskStatus(Integer.parseInt(id), Integer.parseInt(statusId));
						if (isSuccess) {
							resp.sendRedirect("tasks?msg=statusUpdateSuccess");
						} else {
							resp.sendRedirect("tasks?msg=statusUpdateError");
						}
					} else {
						resp.sendRedirect("tasks?msg=accessDenied");
					}
				} else {
					resp.sendRedirect("tasks?msg=invalidParameters");
				}
			} else {
				resp.sendRedirect("tasks?msg=accessDenied");
			}
		}
	}
	
	private String getUserIdFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user_id")) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	private String getUserRoleFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("role")) {
					String roleId = cookie.getValue();
					return getRoleName(roleId);
				}
			}
		}
		return null;
	}
	
	private String getRoleName(String roleId) {
		switch (roleId) {
			case "1": return "ROLE_ADMIN";
			case "2": return "ROLE_LEADER";
			case "3": return "ROLE_MEMBER";
			default: return "UNKNOWN";
		}
	}
	
	private String getUserIdFromEmail(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					String email = cookie.getValue();
					if (email != null && !email.equals("")) {
						entity.Users user = userService.findUserByEmail(email);
						if (user != null) {
							return String.valueOf(user.getId());
						}
					}
					break;
				}
			}
		}
		return null;
	}
}
