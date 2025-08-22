package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import crm_app10.services.RoleService;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RoleControllers", urlPatterns = { "/role" , "/role-add"})
public class RoleControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/role")) {
			List<Role> listRole = roleService.findAllRole();
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		} else if (path.equals("/role-add")) {
			String roleName = req.getParameter("roleName");
			String description = req.getParameter("desc");
			if(roleName!=null) {
				boolean isSuccess = roleService.insertRole(roleName, description);
			}
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		}
	}
}
