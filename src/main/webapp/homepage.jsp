<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="movie.app.Movie" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" href="Styles/homepage.css">
</head>

<body>
    <header>
        <a href="#" class="logo">Logo</a>
        <div class="nav-links">
            <input type="text" placeholder="Search Movie">
            <input type="text" placeholder="Search Theatre">
        </div>
        <button class="profile-btn">Profile</button>
    </header>

    <div class="content">
        <%
            ArrayList<Movie> movieList = (ArrayList<Movie>) request.getAttribute("movieList");
            if (movieList != null) {
                for (Movie movie : movieList) {
        %>
        <a href="#" class="box">
            <%= movie.getMovieName() %>
        </a>
        <%
                }
            } else {
                response.getWriter().print("Movies Not Available");
            }
        %>
    </div>

    <footer>
        <p>&copy; 2024 My Movie Booker</p>
    </footer>
</body>

</html>