package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { 
	"/dashboard", "/user", "/user-*", "/role", "/role-*", "/jobs", "/job-*", 
	"/tasks", "/task-*", "/profile", "/logout" 
})
public class AuthenticationFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println("Kiểm tra AuthenticationFilter Path: " + req.getServletPath());
		
		Cookie[] cookies = req.getCookies();
		boolean isLoggedIn = false;
		String email = null;
		String role = null;
		String userId = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				
				if (name.equals("email") && value != null && !value.equals("")) {
					email = value;
				}
				if (name.equals("role") && value != null && !value.equals("")) {
					role = value;
				}
				if (name.equals("user_id") && value != null && !value.equals("")) {
					userId = value;
				}
			}
		}

		if (email != null && role != null) {
			isLoggedIn = true;
		}
		
		if (isLoggedIn) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
	
}
