package crm_app10.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import crm_app10.services.TasksService;
import crm_app10.services.UserService;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DashboardController", urlPatterns = { "/dashboard" })
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TasksService tasksService = new TasksService();
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lấy tên user từ cookie
		String userName = "Guest";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("email".equals(cookie.getName())) {
					Users user = userService.findUserByEmail(cookie.getValue());
					if (user != null) {
						userName = user.getFullName();
					}
					break;
				}
			}
		}
		req.setAttribute("currentUserName", userName);

		Map<String, Integer> taskStats = tasksService.getTaskStatistics();
		
		req.setAttribute("pendingTasks", taskStats.get("pending"));
		req.setAttribute("inProgressTasks", taskStats.get("inProgress")); 
		req.setAttribute("completedTasks", taskStats.get("completed"));
		req.setAttribute("totalTasks", taskStats.get("total"));
		
		int total = taskStats.get("total");
		if (total > 0) {
			int pendingPercent = (taskStats.get("pending") * 100) / total;
			int inProgressPercent = (taskStats.get("inProgress") * 100) / total;
			int completedPercent = (taskStats.get("completed") * 100) / total;
			
			req.setAttribute("pendingPercent", pendingPercent);
			req.setAttribute("inProgressPercent", inProgressPercent);
			req.setAttribute("completedPercent", completedPercent);
		} else {
			req.setAttribute("pendingPercent", 0);
			req.setAttribute("inProgressPercent", 0);
			req.setAttribute("completedPercent", 0);
		}
		
		List<Map<String, Object>> urgentProjects = tasksService.getUrgentProjects(5);
		List<Map<String, Object>> upcomingTasks = tasksService.getUpcomingTasks(10);
		List<Map<String, Object>> overloadedUsers = tasksService.getOverloadedUsers(5);
		
		req.setAttribute("urgentProjects", urgentProjects);
		req.setAttribute("upcomingTasks", upcomingTasks);
		req.setAttribute("overloadedUsers", overloadedUsers);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
