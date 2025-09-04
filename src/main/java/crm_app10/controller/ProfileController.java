package crm_app10.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import crm_app10.services.TasksService;
import crm_app10.services.UserService;
import entity.Tasks;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfileController", urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private TasksService tasksService = new TasksService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String userIdParam = req.getParameter("id");
			int userId = 1;
			
			try {
				if (userIdParam != null && !userIdParam.isEmpty()) {
					userId = Integer.parseInt(userIdParam);
				}
			} catch (NumberFormatException e) {
				userId = 1;
			}
			
			req.setAttribute("pendingTasks", 0);
			req.setAttribute("inProgressTasks", 0);
			req.setAttribute("completedTasks", 0);
			req.setAttribute("totalTasks", 0);
			req.setAttribute("pendingPercent", 0);
			req.setAttribute("inProgressPercent", 0);
			req.setAttribute("completedPercent", 0);
			
			try {
				Users user = userService.findUserById(userId);
				req.setAttribute("user", user);
			} catch (Exception e) {
				req.setAttribute("user", null);
			}
			
			try {
				List<Tasks> userTasks = tasksService.findTasksByUserId(userId);
				req.setAttribute("userTasks", userTasks);
			} catch (Exception e) {
				req.setAttribute("userTasks", null);
			}
			
			try {
				Map<String, Integer> userStats = tasksService.getUserTaskStatistics(userId);
				if (userStats != null && !userStats.isEmpty()) {
					req.setAttribute("pendingTasks", userStats.getOrDefault("pending", 0));
					req.setAttribute("inProgressTasks", userStats.getOrDefault("inProgress", 0));
					req.setAttribute("completedTasks", userStats.getOrDefault("completed", 0));
					req.setAttribute("totalTasks", userStats.getOrDefault("total", 0));
					
					int total = userStats.getOrDefault("total", 0);
					if (total > 0) {
						req.setAttribute("pendingPercent", (userStats.getOrDefault("pending", 0) * 100) / total);
						req.setAttribute("inProgressPercent", (userStats.getOrDefault("inProgress", 0) * 100) / total);
						req.setAttribute("completedPercent", (userStats.getOrDefault("completed", 0) * 100) / total);
					}
				}
			} catch (Exception e) {
				System.out.println("Error :" + e.getMessage());
			}
			
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			
		} catch (Exception e) {
			// Log error and redirect to dashboard
			e.printStackTrace();
			resp.sendRedirect("dashboard");
		}
	}
}
