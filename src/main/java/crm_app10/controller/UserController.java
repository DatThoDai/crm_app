package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import crm_app10.services.UserServices;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = { "/user","/user-delete" })
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserServices userService = new UserServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if(servletPath.equals("/user-delete")){
			String idUser = req.getParameter("id");
			userService.deleteUser(Integer.parseInt(idUser));
		}
		List<Users> listUsers = userService.findAll();
		req.setAttribute("listUser", listUsers);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		
	}
}
