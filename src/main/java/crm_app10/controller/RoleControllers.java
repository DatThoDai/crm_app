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

@WebServlet(name = "RoleControllers", urlPatterns = { "/role", "/role-add", "/role-delete", "/role-edit" })
public class RoleControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/role")) {
			List<Role> listRole = roleService.findAllRole();
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		} else if (path.equals("/role-add")) {
			req.setAttribute("mode", "add");
			req.setAttribute("pageTitle", "Thêm mới quyền");
			req.setAttribute("buttonText", "Add Role");
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else if (path.equals("/role-edit")) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
					Role role = roleService.findRoleById(id); 
					if (role != null) {
						req.setAttribute("mode", "edit");
						req.setAttribute("pageTitle", "Sửa quyền");
						req.setAttribute("buttonText", "Update Role");
						req.setAttribute("role", role);
						req.getRequestDispatcher("role-add.jsp").forward(req, resp);
					} else {
						resp.sendRedirect("role?error=notfound");
					}
				} catch (NumberFormatException e) {
					resp.sendRedirect("role");
				}
			} else {
				resp.sendRedirect("role");
			}
		} else if (path.equals("/role-delete")) {
			String idRole = req.getParameter("id");
			if (idRole != null) {
				try {
					int id = Integer.parseInt(idRole);
					boolean isSuccess = roleService.deleteRole(id);
					if (isSuccess) {
						resp.sendRedirect("role?deleteSuccess=true");
					} else {
						resp.sendRedirect("role?deleteSuccess=true");
					}
				} catch (Exception e) {
					resp.sendRedirect("role?deleteSuccess=true");
				}
			} else {
				resp.sendRedirect("role");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/role-add")) {
			String roleName = req.getParameter("roleName");
			String description = req.getParameter("desc");
			try {
				boolean isSuccess = roleService.insertRole(roleName, description);
				if (isSuccess) {
					resp.sendRedirect("role-add?success=true");
				} else {
					resp.sendRedirect("role-add?error=true");
				}
			} catch (Exception e) {
				resp.sendRedirect("role-add?error=true");
			}
		}else if (path.equals("/role-edit")) {
            String idParam = req.getParameter("id");
            String roleName = req.getParameter("roleName");
            String description = req.getParameter("desc");
            try {
                int id = Integer.parseInt(idParam);
                boolean isSuccess = roleService.updateRole(id, roleName, description);
                
                if (isSuccess) {
                	resp.sendRedirect("role-edit?id=" + id + "&success=true");
                } else {
                    resp.sendRedirect("role-edit?id=" + id + "&error=true");
                }
            } catch (Exception e) {
                resp.sendRedirect("role?updateError=true");
            }
        }
	}
}
