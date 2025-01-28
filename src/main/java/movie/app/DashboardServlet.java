package movie.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Movie> movieList = new ArrayList<>();

	@Override
	public void init() throws ServletException {
		movieList.add(new Movie(
				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS2uZIj7-mLfVp1TYvfTEPBMGL5fCn8Hm40LEKFPPE6BHVIPA7fDyUM9e5zXKvyWdUyqzXOEg",
				"Kung Fu Panda", "8/10", "2008", 1.30));
		movieList.add(new Movie(
				"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSrEnW4Qes8q3NiwQS3grDaqN5ENSQdFTik1C2fdEeSAXTDQAzs",
				"Kung Fu Panda 2", "8/10", "2011", 2.30));
		movieList.add(
				new Movie("https://lumiere-a.akamaihd.net/v1/images/image_0e6ad10d.jpeg?region=0%2C0%2C1400%2C2100",
						"Kung Fu Panda 3", "8/10", "2015", 2.00));
		movieList.add(new Movie(
				"https://assets.gadgets360cdn.com/pricee/assets/product/202311/Kung-Fu-Panda-4-Poster_1700827659.jpg",
				"Kung Fu Panda 4", "5/10", "2024", 1.30));
		movieList.add(new Movie(
				"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTJOxjD49uzIVF4gBVI_eopbb0AS53-Ta-wExU2C8s8hlDN5UQs-fXihh4AYxJnwh2ShJNj",
				"Die Hard 4", "7/10", "2007", 1.30));
		movieList.add(new Movie(
				"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQUuM488ffxIbhzFEUA-rimo81Gmbnfo1R1n_XH8ga09-Fy5W2rG3NcKESX0UorZrXEBVzq_Q",
				"Mr. Bean", "9/10", "2008", 1.30));
		movieList.add(
				new Movie("https://m.media-amazon.com/images/I/91vIHsL-zjL.jpg", "Interstellar", "8/10", "2011", 1.30));
		movieList.add(new Movie(
				"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcROqmrMVzOXm78bp2aJec_ZFj5qXEnRLZAiCFXTxygy7w_f1d-k2GJ0kwuqhRVxIUkut4vSJQ",
				"Alien", "8/10", "1967", 1.30));
		movieList.add(new Movie(
				"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRNF36FavofYcx1JFn6em8xKWcHYqu5dBg-TeB_I_FWIwrqsw2WxE3BDy13Y6q9XavceWwYGA",
				"Aliens", "8/10", "1978", 2.00));
		movieList.add(new Movie(
				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTMQp_9_bE2JOeH4JtApxU0rDthouxCNR_TvZ3c-mb8FROFwS3PWXTLeLUEpnBA7TpT7k3x",
				"Cars", "7/10", "2012", 2.00));
		movieList.add(new Movie(
				"https://www.sonypictures.com/sites/default/files/styles/max_560x840/public/chameleon/title-movie/244665_open_season_2006_1400x2100.jpg?itok=YLszgqmY",
				"Open Season", "7/10", "2011", 1.30));
		movieList.add(new Movie(
				"https://lumiere-a.akamaihd.net/v1/images/p_walle_19753_69f7ff00.jpeg?region=0%2C0%2C540%2C810",
				"Wall E", "9/10", "2006", 2.30));
	}

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
			response.getWriter().println("Not authenticated. Redirecting to login...");
			response.sendRedirect("index.html");
			return;
		}

		StringBuffer html = new StringBuffer();

		html.append("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Homepage</title>\r\n" 
				+ "        <style>\r\n"
				+ "* {\r\n"
				+ "    margin: 0;\r\n"
				+ "    padding: 0;\r\n"
				+ "    box-sizing: border-box;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "body {\r\n"
				+ "    font-family: Arial, sans-serif;\r\n"
				+ "    background-color: #f9fafb;\r\n"
				+ "    color: #333;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-header {\r\n"
				+ "    display: flex;\r\n"
				+ "    justify-content: space-between;\r\n"
				+ "    align-items: center;\r\n"
				+ "    padding: 20px;\r\n"
				+ "    background-color: #1f2937;\r\n"
				+ "    color: white;\r\n"
				+ "    border-bottom: 2px solid #ef4444;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-header img {\r\n"
				+ "    width: 50px;\r\n"
				+ "    height: 50px;\r\n"
				+ "    border-radius: 50%;\r\n"
				+ "    border: 2px solid #ef4444;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-header input[type=\"text\"] {\r\n"
				+ "    padding: 8px;\r\n"
				+ "    margin: 0 5px;\r\n"
				+ "    border: 2px solid #ef4444;\r\n"
				+ "    border-radius: 5px;\r\n"
				+ "    font-size: 14px;\r\n"
				+ "    transition: border-color 0.3s;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-header input[type=\"text\"]:focus {\r\n"
				+ "    border-color: #dc2626;\r\n"
				+ "    outline: none;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-header button {\r\n"
				+ "    padding: 8px 16px;\r\n"
				+ "    border: 2px solid #ef4444;\r\n"
				+ "    background-color: #ef4444;\r\n"
				+ "    color: white;\r\n"
				+ "    font-size: 16px;\r\n"
				+ "    font-weight: bold;\r\n"
				+ "    cursor: pointer;\r\n"
				+ "    border-radius: 10px;\r\n"
				+ "    transition: background-color 0.3s;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-header button:hover {\r\n"
				+ "    background-color: #dc2626;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-body {\r\n"
				+ "    padding: 20px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-showcase {\r\n"
				+ "    text-align: center;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-showcase h1 {\r\n"
				+ "    font-size: 28px;\r\n"
				+ "    margin-bottom: 20px;\r\n"
				+ "    color: #1f2937;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".card-container {\r\n"
				+ "    display: grid;\r\n"
				+ "    grid-template-columns: repeat(4, 1fr);\r\n"
				+ "    gap: 20px;\r\n"
				+ "    padding: 10px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".box {\r\n"
				+ "    background-color: white;\r\n"
				+ "    border-radius: 10px;\r\n"
				+ "    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\r\n"
				+ "    padding: 20px;\r\n"
				+ "    text-align: center;\r\n"
				+ "    transition: transform 0.3s ease, box-shadow 0.3s ease;\r\n"
				+ "    height: 350px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".box:hover {\r\n"
				+ "    transform: translateY(-5px);\r\n"
				+ "    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".box img {\r\n"
				+ "    width: 100%;\r\n"
				+ "    height: 200px;\r\n"
				+ "    object-fit: cover;\r\n"
				+ "    border-radius: 8px;\r\n"
				+ "    margin-bottom: 15px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".box h4 {\r\n"
				+ "    font-size: 18px;\r\n"
				+ "    color: #4b5563;\r\n"
				+ "    margin: 5px 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".box .rating {\r\n"
				+ "    font-size: 16px;\r\n"
				+ "    color: #ef4444;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-footer {\r\n"
				+ "    text-align: center;\r\n"
				+ "    padding: 10px;\r\n"
				+ "    background-color: #1f2937;\r\n"
				+ "    color: white;\r\n"
				+ "    font-size: 14px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#main-footer p {\r\n"
				+ "    margin-top: 5px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "    </style>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
				+ "    <header id=\"main-header\">\r\n" + "        <img src=\"\" alt=\"Logo\">\r\n"
				+ "        <input type=\"text\" placeholder=\"Search movies\">\r\n"
				+ "        <input type=\"text\" placeholder=\"Search theatres\">\r\n"
				+ "        <button>Profile</button>\r\n" + "    </header>\r\n" + "    <main id=\"main-body\">\r\n"
				+ "        <section id=\"main-showcase\">\r\n" + "            <h1>Movies</h1>\r\n"
				+ "            <div class=\"card-container\">");

		for (Movie movie : movieList) {
			html.append("<div class=\"box\">\r\n" + "                    <img src=" + movie.getMovieUrl()
					+ " alt=\"Movie image\" />\r\n" + "                    <h4>Movie Name: " + movie.getMovieName()
					+ "</h4>\r\n" + "                    <h4>Movie Rating: " + movie.getMovieRating() + "</h4>\r\n"
					+ "                    <h4>Release Year: " + movie.getReleaseYear() + "</h4>\r\n"
					+ "                    <h4>Duration: " + movie.getDuration() + "</h4>\r\n"
					+ "                </div>");
		}

		html.append("</div>\r\n" + "        </section>\r\n" + "    </main>\r\n" + "    <footer id=\"main-footer\">\r\n"
				+ "        <p>&copy; My Movie Booker</p>\r\n" + "    </footer>\r\n" + "</body>\r\n" + "\r\n"
				+ "</html>\r\n" + "");

		response.getWriter().print(html.toString());
	}
}
