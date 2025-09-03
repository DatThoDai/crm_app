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

@WebServlet(name = "UserController", urlPatterns = { "/user", "/user-delete", "/user-add" })
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserServices userService = new UserServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		if(servletPath.equals("/user-delete")){
			// Xử lý xóa user
			String idUser = req.getParameter("id");
			
			if (idUser != null) {
				try {
					boolean isSuccess = userService.deleteUser(Integer.parseInt(idUser));
					
					if (isSuccess) {
						resp.sendRedirect("user?deleteSuccess=true");
					} else {
						resp.sendRedirect("user?deleteError=true");
					}
				} catch (NumberFormatException e) {
					resp.sendRedirect("user?deleteError=true");
				}
			} else {
				resp.sendRedirect("user");
			}
			
		} else if(servletPath.equals("/user-add")) {
			// Hiển thị form thêm user
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			
		} else {
			// Hiển thị danh sách user
			List<Users> listUsers = userService.findAll();
			req.setAttribute("listUser", listUsers);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		if(servletPath.equals("/user-add")) {
			// Xử lý thêm user mới
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String fullName = req.getParameter("fullName");
			String roleId = req.getParameter("roleId");
			
			try {
				boolean isSuccess = userService.addUser(email, password, fullName, Integer.parseInt(roleId));
				
				if (isSuccess) {
					resp.sendRedirect("user-add?success=true");
				} else {
					resp.sendRedirect("user-add?error=true");
				}
			} catch (Exception e) {
				System.out.println("Add user error: " + e.getMessage());
				resp.sendRedirect("user-add?error=true");
			}
		}
	}
}
