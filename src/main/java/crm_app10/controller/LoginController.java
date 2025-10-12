package crm_app10.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookies = req.getCookies();
		String email = "";
		String password = "";
		
		for(Cookie cookie : listCookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			
			if(name.equals("email")) {
				email = value;
			}
			
			if (name.equals("password")) {
				password = value;
			}
		}
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");	
	
		String query = "SELECT * FROM users WHERE email = ? AND password = ?";

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Users> listUsers = new ArrayList<Users>();
			
			while (resultSet.next()) {
				Users user = new Users();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullName(resultSet.getString("fullname"));
				user.setRoleId(resultSet.getInt("role_id"));
				listUsers.add(user);
			}
			
			if(listUsers.isEmpty()) {
				System.out.println("Dang nhap that bai");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				System.out.println("Dang nhap thanh cong");
				
				Users currentUser = listUsers.get(0);
				
				Cookie cEmail = new Cookie("email", email);
				cEmail.setMaxAge(8 * 60 * 60);
				resp.addCookie(cEmail);
				
				Cookie cRole = new Cookie("role", currentUser.getRoleId() + "");
				cRole.setMaxAge(8 * 60 * 60);
				resp.addCookie(cRole);

				Cookie cUserId = new Cookie("user_id", currentUser.getId() + "");
				cUserId.setMaxAge(8 * 60 * 60);
				resp.addCookie(cUserId);
				
				if(remember != null) {
					Cookie cPassword = new Cookie("password", password);
					cPassword.setMaxAge(5 * 60);
					resp.addCookie(cPassword);
				}
				resp.sendRedirect("dashboard");
				return;
			}
		} catch (Exception e) {
			System.out.println("Lỗi thực thi câu truy vấn : " + e.getMessage());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
