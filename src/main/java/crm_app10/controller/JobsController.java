package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import crm_app10.services.JobsService;
import entity.Jobs;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "JobsController", urlPatterns = { "/jobs" })
public class JobsController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Jobs> listJobs = new JobsService().findAllJobs();
		req.setAttribute("listJobs", listJobs);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}
}
