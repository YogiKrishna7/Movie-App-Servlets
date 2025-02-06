-- Movie Booking System Database Schema

CREATE DATABASE movie_app;
USE movie_app;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO Users (name, phone, email, password)
VALUES
('abc', '1234567890', 'abc@example.com', '123'),
('def', '1234567891', 'def@example.com', '1234'),
('ghi', '1234567892', 'ghi@example.com', '12345');

CREATE TABLE Payment_Methods (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    payment_type ENUM('Credit Card', 'Debit Card', 'UPI'),
    card_number VARCHAR(16),  -- Only for cards
    upi_id VARCHAR(50),  -- Only for UPI
    expiry_date DATE,  -- Only for cards
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    movieUrl VARCHAR(256),
    title VARCHAR(255),
    description TEXT,
    genre VARCHAR(100),
    duration INT,
    release_year INT
);

INSERT INTO Movies (movieUrl, title, description, genre, duration, release_year)
VALUES
('https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS2uZIj7-mLfVp1TYvfTEPBMGL5fCn8Hm40LEKFPPE6BHVIPA7fDyUM9e5zXKvyWdUyqzXOEg', 'Kung Fu Panda', 'An animated movie about a kung fu enthusiast panda.', 'Animation', 90, 2008),
('https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSrEnW4Qes8q3NiwQS3grDaqN5ENSQdFTik1C2fdEeSAXTDQAzs', 'Kung Fu Panda 2', 'The sequel to Kung Fu Panda, where Po faces a new challenge.', 'Animation', 120, 2011),
('https://lumiere-a.akamaihd.net/v1/images/image_0e6ad10d.jpeg?region=0%2C0%2C1400%2C2100', 'Kung Fu Panda 3', 'Po reunites with his biological father and must stop a villain.', 'Animation', 120, 2015),
('https://assets.gadgets360cdn.com/pricee/assets/product/202311/Kung-Fu-Panda-4-Poster_1700827659.jpg', 'Kung Fu Panda 4', 'The latest installment where Po faces a new adventure.', 'Animation', 90, 2024),
('https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTJOxjD49uzIVF4gBVI_eopbb0AS53-Ta-wExU2C8s8hlDN5UQs-fXihh4AYxJnwh2ShJNj', 'Die Hard 4', 'The fourth installment of the Die Hard series, with John McClane.', 'Action', 90, 2007),
('https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQUuM488ffxIbhzFEUA-rimo81Gmbnfo1R1n_XH8ga09-Fy5W2rG3NcKESX0UorZrXEBVzq_Q', 'Mr. Bean', 'The comedic adventures of Mr. Bean.', 'Comedy', 90, 2008);

CREATE TABLE Theatres (
    theatre_id INT PRIMARY KEY AUTO_INCREMENT,
    theatre_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE Showtimes (
    showtime_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT,
    theatre_id INT,
    show_date DATE,
    show_time TIME,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id) ON DELETE CASCADE,
    FOREIGN KEY (theatre_id) REFERENCES Theatres(theatre_id) ON DELETE CASCADE
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    showtime_id INT,
    total_price DECIMAL(10,2),
    status ENUM('Pending', 'Confirmed', 'Cancelled') DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (showtime_id) REFERENCES Showtimes(showtime_id) ON DELETE CASCADE
);

CREATE TABLE Seat_Selection (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    showtime_id INT,
    seat_number VARCHAR(5) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (showtime_id) REFERENCES Showtimes(showtime_id) ON DELETE CASCADE
);

CREATE TABLE Reviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    movie_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id) ON DELETE CASCADE
);
