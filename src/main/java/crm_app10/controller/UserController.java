package crm_app10.controller;

import java.io.IOException;
import java.util.List;

import crm_app10.services.RoleService;
import crm_app10.services.UserService;
import entity.Role;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = { "/user", "/user-delete", "/user-add", "/user-edit", "/user-details" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		if (servletPath.equals("/user-delete")) {
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

		} else if (servletPath.equals("/user-add")) {
			List<Role> listRoles = roleService.findAllRole();
			req.setAttribute("listRoles", listRoles);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (servletPath.equals("/user-edit")) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
					Users user = userService.findUserById(id);
					List<Role> listRoles = roleService.findAllRole();
					if (user != null) {
						req.setAttribute("mode", "edit");
						req.setAttribute("pageTitle", "Chỉnh sửa thành viên");
						req.setAttribute("buttonText", "Update User");
						req.setAttribute("user", user);
						req.setAttribute("listRoles", listRoles);
						req.getRequestDispatcher("user-add.jsp").forward(req, resp);
					} else {
						resp.sendRedirect("user?error=notfound");
					}
				} catch (NumberFormatException e) {
					resp.sendRedirect("user");
				}
			} else {
				resp.sendRedirect("user");
			}

		} else if (servletPath.equals("/user-details")) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
					Users user = userService.findUserById(id);
					if (user != null) {
						// Lấy thống kê task của user
						var userStats = userService.getUserTaskStatistics(id);
						// Lấy danh sách task của user
						var userTasks = userService.getTasksByUserId(id);
						
						req.setAttribute("user", user);
						req.setAttribute("userStats", userStats);
						req.setAttribute("userTasks", userTasks);
						req.getRequestDispatcher("user-details.jsp").forward(req, resp);
					} else {
						resp.sendRedirect("user?error=notfound");
					}
				} catch (NumberFormatException e) {
					resp.sendRedirect("user");
				}
			} else {
				resp.sendRedirect("user");
			}

		} else {
			List<Users> listUsers = userService.findAll();
			req.setAttribute("listUser", listUsers);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		if (servletPath.equals("/user-add")) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String fullName = req.getParameter("fullName");
			String roleId = req.getParameter("roleId");

			try {
				boolean isSuccess = userService.addUser(email, password, fullName, Integer.parseInt(roleId));

				if (isSuccess) {
					resp.sendRedirect("user?addSuccess=true");
				} else {
					resp.sendRedirect("user?addError=true");
				}
			} catch (Exception e) {
				System.out.println("Add user error: " + e.getMessage());
				resp.sendRedirect("user?addError=true");
			}
		} else if (servletPath.equals("/user-edit")) {
			String idParam = req.getParameter("id");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String fullName = req.getParameter("fullName");
			String roleId = req.getParameter("roleId");

			try {
				int id = Integer.parseInt(idParam);
				boolean isSuccess = userService.updateUser(id, email, password, fullName, Integer.parseInt(roleId));
				if (isSuccess) {
					resp.sendRedirect("user?updateSuccess=true");
				} else {
					resp.sendRedirect("user-edit?id=" + id + "&error=true");
				}
			} catch (Exception e) {
				System.out.println("Update user error: " + e.getMessage());
				resp.sendRedirect("user?updateError=true");
			}
		}
	}
}
