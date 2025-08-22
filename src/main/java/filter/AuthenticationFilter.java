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

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { "/user" })
public class AuthenticationFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Cookie[] cookies = req.getCookies();
		boolean isLogined = false;
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals("role")) {
				isLogined = true;
			}
		}
		if (isLogined) {
			//cho đi tiếp
			chain.doFilter(request, response);
		}else {
			// chuyển hướng về trang login
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}
	
}
