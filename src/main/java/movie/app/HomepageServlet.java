package movie.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private ArrayList<Movie> movieList = new ArrayList<>();

//	@Override
//	public void init() throws ServletException {
//		movieList.add(new Movie(
//				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS2uZIj7-mLfVp1TYvfTEPBMGL5fCn8Hm40LEKFPPE6BHVIPA7fDyUM9e5zXKvyWdUyqzXOEg",
//				"Kung Fu Panda", "8/10", "2008", 1.30));
//		movieList.add(new Movie(
//				"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSrEnW4Qes8q3NiwQS3grDaqN5ENSQdFTik1C2fdEeSAXTDQAzs",
//				"Kung Fu Panda 2", "8/10", "2011", 2.30));
//		movieList.add(
//				new Movie("https://lumiere-a.akamaihd.net/v1/images/image_0e6ad10d.jpeg?region=0%2C0%2C1400%2C2100",
//						"Kung Fu Panda 3", "8/10", "2015", 2.00));
//		movieList.add(new Movie(
//				"https://assets.gadgets360cdn.com/pricee/assets/product/202311/Kung-Fu-Panda-4-Poster_1700827659.jpg",
//				"Kung Fu Panda 4", "5/10", "2024", 1.30));
//		movieList.add(new Movie(
//				"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTJOxjD49uzIVF4gBVI_eopbb0AS53-Ta-wExU2C8s8hlDN5UQs-fXihh4AYxJnwh2ShJNj",
//				"Die Hard 4", "7/10", "2007", 1.30));
//		movieList.add(new Movie(
//				"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQUuM488ffxIbhzFEUA-rimo81Gmbnfo1R1n_XH8ga09-Fy5W2rG3NcKESX0UorZrXEBVzq_Q",
//				"Mr. Bean", "9/10", "2008", 1.30));
//		movieList.add(
//				new Movie("https://m.media-amazon.com/images/I/91vIHsL-zjL.jpg", "Interstellar", "8/10", "2011", 1.30));
//		movieList.add(new Movie(
//				"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcROqmrMVzOXm78bp2aJec_ZFj5qXEnRLZAiCFXTxygy7w_f1d-k2GJ0kwuqhRVxIUkut4vSJQ",
//				"Alien", "8/10", "1967", 1.30));
//		movieList.add(new Movie(
//				"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRNF36FavofYcx1JFn6em8xKWcHYqu5dBg-TeB_I_FWIwrqsw2WxE3BDy13Y6q9XavceWwYGA",
//				"Aliens", "8/10", "1978", 2.00));
//		movieList.add(new Movie(
//				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTMQp_9_bE2JOeH4JtApxU0rDthouxCNR_TvZ3c-mb8FROFwS3PWXTLeLUEpnBA7TpT7k3x",
//				"Cars", "7/10", "2012", 2.00));
//		movieList.add(new Movie(
//				"https://www.sonypictures.com/sites/default/files/styles/max_560x840/public/chameleon/title-movie/244665_open_season_2006_1400x2100.jpg?itok=YLszgqmY",
//				"Open Season", "7/10", "2011", 1.30));
//		movieList.add(new Movie(
//				"https://lumiere-a.akamaihd.net/v1/images/p_walle_19753_69f7ff00.jpeg?region=0%2C0%2C540%2C810",
//				"Wall E", "9/10", "2006", 2.30));
//	}

	String url = "jdbc:mysql://localhost:3306/movie_app";
	String db_username = "root";
	String db_password = "yogi";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isAuthenticated = false;
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					isAuthenticated = true;
					break;
				}
			}
		}

		if (!isAuthenticated) {
			response.getWriter().println("Not authenticated");
			response.sendRedirect("index.jsp");
			return;
		}

		// Servlet Context
//		ServletContext sc = getServletContext();
//		String name = sc.getInitParameter("Name");
//		
//		PrintWriter out = response.getWriter();
//		out.print("Hello " + name);

//		request.setAttribute("movieList", movieList);
//	    request.getRequestDispatcher("homepage.jsp").forward(request, response);

		ArrayList<Movie> movieList = new ArrayList<Movie>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, db_username, db_password);
			PreparedStatement ps = con.prepareStatement("select * from Movies");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String movieUrl = rs.getString("movieUrl");
                String movieTitle = rs.getString("title");
                String movieDescription = rs.getString("description");
                String movieGenre = rs.getString("genre");
                int releaseYear = rs.getInt("release_year");
                double duration = rs.getDouble("duration");

                movieList.add(new Movie(movieUrl, movieTitle, movieDescription, movieGenre, releaseYear, duration));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("movieList", movieList);
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
	}
}
