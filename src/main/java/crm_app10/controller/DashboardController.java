package crm_app10.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import crm_app10.services.TasksService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DashboardController", urlPatterns = { "/dashboard", "/" })
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TasksService tasksService = new TasksService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
