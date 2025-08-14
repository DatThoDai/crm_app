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

@WebServlet(name = "UserController", urlPatterns = { "/user" })
public class UserController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Users> listUsers = new UserServices().findAll();
		req.setAttribute("listUser", listUsers);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
}
