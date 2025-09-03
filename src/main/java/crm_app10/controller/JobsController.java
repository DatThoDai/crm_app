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

@WebServlet(name = "JobsController", urlPatterns = { "/jobs", "/job-add", "/job-delete", "/job-edit" })
public class JobsController extends HttpServlet {
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
		} else if (path.equals("/job-delete")) {
			String idJob = req.getParameter("id");

			if (idJob != null) {
				try {
					boolean isSuccess = jobsService.deleteJob(Integer.parseInt(idJob));

					if (isSuccess) {
						resp.sendRedirect("jobs?deleteSuccess=true");
					} else {
						resp.sendRedirect("jobs?deleteError=true");
					}
				} catch (NumberFormatException e) {
					resp.sendRedirect("jobs?deleteError=true");
				}
			} else {
				resp.sendRedirect("jobs");
			}
		}else if (path.equals("/job-edit")) {
	        String idParam = req.getParameter("id");
	        if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
					Jobs job = jobsService.findJobById(id);
					if (job != null) {
	                    req.setAttribute("mode", "edit");
	                    req.setAttribute("pageTitle", "Sửa dự án");
	                    req.setAttribute("buttonText", "Update Project");
	                    req.setAttribute("job", job);
	                    req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	                } else {
	                    resp.sendRedirect("jobs?error=notfound");
	                }
	            } catch (NumberFormatException e) {
	                resp.sendRedirect("jobs");
	            }
	        } else {
	            resp.sendRedirect("jobs");
	        }
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
		}else if (path.equals("/job-edit")) {
	        String idParam = req.getParameter("id");
	        String jobName = req.getParameter("jobName");
	        String startDate = req.getParameter("startDate");
	        String endDate = req.getParameter("endDate");
	        
	        if (idParam != null) {
	            try {
	                int id = Integer.parseInt(idParam);
	                boolean isSuccess = jobsService.updateJob(id, jobName, startDate, endDate);
	                if (isSuccess) {
	                    resp.sendRedirect("jobs?updateSuccess=true");
	                } else {
	                    resp.sendRedirect("jobs?updateError=true");
	                }
	            } catch (NumberFormatException e) {
	                resp.sendRedirect("jobs?updateError=true");
	            }
	        } else {
	            resp.sendRedirect("jobs?updateError=true");
	        }
	    }
	}
}
