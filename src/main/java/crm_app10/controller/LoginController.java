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
			//getName(): Trả tên cookie
			String name = cookie.getName();
			//getValue(): Trả giá trị lưu trữ của cookie
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
		// chuan bi cau truy van , sql injection la nguy hiem ( tiem cau truy van vao tham so thay vi gia tri thong thuong ) , tim hieu IBM ESB TOOL, NODE RED, MULESOFT,SOLID 
		String query = "SELECT * FROM users WHERE email = ? AND password = ?";
		// mo ket noi co so du lieu
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//truyen cau truy van vao connection moi vua ket noi
			/*
			 *excuteQuery() : thuc thi cau truy van select
			 *excuteUpdate() : thuc thi cau truy van insert, update, delete 
			 */
			// set tham so cho dau cham hoi ben trong cau query 
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			//tao mot danh sacxh rong bien du lieu tu cau truy van trong resultSet thanh mang / danh sach
			List<Users> listUsers = new ArrayList<Users>();
			
			while (resultSet.next()) {
				Users user = new Users();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullName(resultSet.getString("fullname"));
				
				listUsers.add(user);
				
				if(listUsers.isEmpty()) {
					System.out.println("Dang nhap that bai");
				}else {
					System.out.println("Dang nhap thanh cong");
					if(remember!=null) {
						//Tạo cookie có tên là email và giá trị lưu trữ là email người dùng nhập
						Cookie cEmail = new Cookie("email", email);
						cEmail.setMaxAge(5 * 60);
						Cookie cPassword = new Cookie("password", password);
						cPassword.setMaxAge(5 * 60);
						
						// bắt client tạo ra cookie
						resp.addCookie(cPassword);
						resp.addCookie(cEmail);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Lỗi thực thi câu truy vấn : " + e.getMessage());
		}
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}
