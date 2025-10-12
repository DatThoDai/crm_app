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

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/login" })
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println("Kiểm tra LoginFilterPath: " + request.getServletPath());

        Cookie[] cookies = request.getCookies();
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
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
