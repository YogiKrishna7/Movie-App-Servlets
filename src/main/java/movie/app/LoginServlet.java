package movie.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	static ArrayList<User> userList = new ArrayList<>();
//
//	@Override
//	public void init() throws ServletException {
//		userList.add(new User("Yogendra", "9866709807", "yogendra@gmail.com", "1234567"));
//		userList.add(new User("Prasad", "1234567891", "prasad@gmail.com", "456"));
//		userList.add(new User("Steve", "1234567892", "steve@gmail.com", "789"));
//	}

	String url = "jdbc:mysql://localhost:3306/movie_app";
	String db_username = "root";
	String db_password = "yogi";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String phoneNumber = request.getParameter("phone");
		String pass = request.getParameter("password");

//		for (User user : userList) {
//			if (user.getPhoneNumber().equals(phone)) {
//				if (user.getPassword().equals(password)) {
//
//					Cookie loginCookie = new Cookie("user", phone);
//					loginCookie.setMaxAge(3600);
//					response.addCookie(loginCookie);
//
////					For cookies we cant use RequestDispatcher because the browser doesn't receive the response with the cookies.
////	              	Thus, cookies are unavailable in the target servlet during the same request lifecycle.
//
////	              	RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard");
////	              	dispatcher.forward(request, response);
//
////	 			  	So when we use the cookies, we can use sendRedirect method so the to ensure cookies are sent back to the server in a new request.
////	              	RequestDispatcher.forward only for internal navigation when cookies or a new request lifecycle are not required.
//					
//					
////					Use RequestDispatcher.forward() for internal navigation within the same request lifecycle, 
////					but avoid it when setting cookies since they won't reach the client. Instead, use response.sendRedirect(), 
////					which triggers a new request where cookies are sent and available in the target servlet.
//
//					response.sendRedirect("homepage");
//				}
//			}
//		}
//
//		response.getWriter().println("Account Not Present");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url, db_username, db_password);
					PreparedStatement ps = con.prepareStatement("select * from Users where phone = ? and password = ?")) {

				ps.setString(1, phoneNumber);
				ps.setString(2, pass);

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Cookie loginCookie = new Cookie("user", phoneNumber);
					loginCookie.setMaxAge(3600);
					response.addCookie(loginCookie);
					
					response.sendRedirect("homepage");
				} else {
					response.sendRedirect("index.jsp");
					System.out.println("Invalid Username or password");
				}
			}

		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
		
	}
}
