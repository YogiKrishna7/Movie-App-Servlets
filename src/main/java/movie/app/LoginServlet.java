package movie.app;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ArrayList<User> userList = new ArrayList<>();

	static {
		userList.add(new User("Yogendra", "9866709807", "yogendra@gmail.com", "1234567"));
		userList.add(new User("Prasad", "1234567891", "prasad@gmail.com", "456"));
		userList.add(new User("Steve", "1234567892", "steve@gmail.com", "789"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		for (User user : userList) {
			if (user.getPhoneNumber().equals(phone)) {
				if (user.getPassword().equals(password)) {

					Cookie loginCookie = new Cookie("user", phone);
					loginCookie.setMaxAge(120);
					loginCookie.setPath("/");
					response.addCookie(loginCookie);

//					For cookies we cant use RequestDispatcher because the browser doesn't receive the response with the cookies.
//	              	Thus, cookies are unavailable in the target servlet during the same request lifecycle.

//	              	RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard");
//	              	dispatcher.forward(request, response);

//	 			  	So when we use the cookies, we can use sendRedirect method so the to ensure cookies are sent back to the server in a new request.
//	              	RequestDispatcher.forward only for internal navigation when cookies or a new request lifecycle are not required.

					response.sendRedirect("DashboardServlet");
				}
			}
		}

		response.getWriter().println("Account Not Present");
	}
}
