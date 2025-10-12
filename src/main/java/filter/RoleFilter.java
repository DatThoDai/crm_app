package filter;

import java.io.IOException;

import crm_app10.services.TasksService;
import entity.Tasks;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName = "RoleFilter", urlPatterns = { 
    "/role-add", "/role-delete", "/role-edit",
    "/user-add", "/user-delete", "/user-edit", 
    "/job-add", "/job-delete", "/job-edit",
    "/task-add", "/task-delete", "/task-edit"
})
public class RoleFilter implements Filter {
    
    private TasksService tasksService = new TasksService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        
        System.out.println("Kiểm tra RoleFilterPath: " + path); 
        Cookie[] cookies = request.getCookies();
        String userRole = null;
        String userId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                
                if (name.equals("role")) {
                    userRole = value;
                }
                if (name.equals("user_id")) {
                    userId = value;
                }
            }
        }

        if (userRole == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String roleName = getRoleName(userRole);

        if (path.startsWith("/role-add") || path.startsWith("/role-delete") || path.startsWith("/role-edit") ||
            path.startsWith("/user-add") || path.startsWith("/user-delete") || path.startsWith("/user-edit") ||
            path.startsWith("/job-add") || path.startsWith("/job-delete") || path.startsWith("/job-edit")) {

            if ("ROLE_ADMIN".equals(roleName)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(request.getContextPath() + "/404.html");
            }

        } else if (path.startsWith("/task-add") || path.startsWith("/task-delete") || path.startsWith("/task-edit")) {
            if ("ROLE_ADMIN".equals(roleName) || "ROLE_LEADER".equals(roleName)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } 
            else if ("ROLE_MEMBER".equals(roleName) && path.equals("/task-edit")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                response.sendRedirect(request.getContextPath() + "/404.html");
            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getRoleName(String roleId) {
        switch (roleId) {
            case "1":
                return "ROLE_ADMIN";
            case "2":
                return "ROLE_LEADER";
            case "3":
                return "ROLE_MEMBER";
            default:
                return "UNKNOWN";
        }
    }
}
