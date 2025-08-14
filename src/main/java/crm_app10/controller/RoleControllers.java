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

@WebServlet(name = "RoleControllers", urlPatterns = { "/role" })
public class RoleControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> listRole = new RoleService().findAllRole();
		req.setAttribute("listRole", listRole);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}
}
