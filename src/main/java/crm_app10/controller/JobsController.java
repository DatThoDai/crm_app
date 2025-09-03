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

@WebServlet(name = "JobsController", urlPatterns = { "/jobs","/job-add" })
public class JobsController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private JobsService jobsService = new JobsService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/jobs")) {
			List<Jobs> listJobs = jobsService.findAllJobs();
			req.setAttribute("listJobs", listJobs);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (path.equals("/job-add")) {
			req.setAttribute("mode", "add");
            req.setAttribute("pageTitle", "Thêm mới dự án");
            req.setAttribute("buttonText", "Add Project");
            req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        if (path.equals("/job-add")) {
            String name = req.getParameter("jobName");
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            
            try {
                boolean isSuccess = jobsService.addJob(name, startDate, endDate);
                
                if (isSuccess) {
                    resp.sendRedirect("jobs?addSuccess=true");
                } else {
                    resp.sendRedirect("jobs?addError=true");
                }
            } catch (Exception e) {
                System.out.println("Add job error: " + e.getMessage());
                resp.sendRedirect("jobs?addError=true");
            }
        }
    }
}
