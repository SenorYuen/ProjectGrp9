-- Create Database and Select it
CREATE DATABASE IF NOT EXISTS movietheaterdb;
USE movietheaterdbperson;

-- ================================================
-- Section 1: Table Definitions
-- ================================================
-- ================================================
-- Section 1: Table Definitions
-- ================================================

-- 1.1 Person Table
CREATE TABLE IF NOT EXISTS Person (
    person_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    user_type ENUM('admin', 'registered', 'regular') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CHECK (email LIKE '%@%') -- Basic email format validation
);

-- 1.2 Movie Table
CREATE TABLE IF NOT EXISTS Movie (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    genre VARCHAR(50),
    description TEXT,
    start_showing DATE NOT NULL,
    end_showing DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 1.3 Showtime Table
CREATE TABLE IF NOT EXISTS Showtime (
    showtime_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  
    movie_id INT NOT NULL,                               
    show_time TIME NOT NULL,                             
    FOREIGN KEY (movie_id) REFERENCES Movie(movie_id)   
    ON DELETE CASCADE                                    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 1.4 Seat Table
CREATE TABLE IF NOT EXISTS Seat (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    `row` CHAR(1) NOT NULL,
    column_number INT NOT NULL,
    taken BOOLEAN DEFAULT FALSE,
    UNIQUE (`row`, column_number)
);

-- 1.5 Theater Table
CREATE TABLE IF NOT EXISTS Theater (
    theater_id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL 
);

-- 1.6 TheaterMovie Join Table
CREATE TABLE IF NOT EXISTS TheaterMovie (
    theater_id INT NOT NULL,
    movie_id INT NOT NULL,
    PRIMARY KEY (theater_id, movie_id),
    FOREIGN KEY (theater_id) REFERENCES Theater(theater_id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie(movie_id) ON DELETE CASCADE
);

-- 1.7 RegisteredCustomer Table
-- 1.8 RegisteredCustomer Table
CREATE TABLE IF NOT EXISTS RegisteredCustomer (
    reg_customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- Ensure this stores plain text
    address VARCHAR(255) NOT NULL,
    card_number VARCHAR(20) NOT NULL
);

-- 1.8 Ticket Table
CREATE TABLE IF NOT EXISTS Ticket (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    seat_id INT NOT NULL,
    showtime_id INT NOT NULL,
    customer_id INT NOT NULL,
    status ENUM('active', 'canceled') DEFAULT 'active',
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seat_id) REFERENCES Seat(seat_id) ON DELETE CASCADE,
    FOREIGN KEY (showtime_id) REFERENCES Showtime(showtime_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES RegisteredCustomer(reg_customer_id) ON DELETE CASCADE,
    UNIQUE (`seat_id`, `showtime_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 1.9 RegularCustomer Table
CREATE TABLE IF NOT EXISTS RegularCustomer (
    regular_customer_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    ticket_id INT,
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id) ON DELETE SET NULL
);

-- 1.10 Payment Table
CREATE TABLE IF NOT EXISTS Payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    payment_method ENUM('credit_card', 'debit_card', 'paypal', 'other') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id) ON DELETE CASCADE
);

-- 1.11 Receipt Table
CREATE TABLE IF NOT EXISTS Receipt (
    receipt_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    movie_id INT NOT NULL,
    seat_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id),
    FOREIGN KEY (movie_id) REFERENCES Movie(movie_id),
    FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
);

-- 1.12 Credit Table
CREATE TABLE IF NOT EXISTS Credit (
    credit_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    credit_amount DECIMAL(10, 2) NOT NULL,
    expiration_date DATE NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id) ON DELETE CASCADE
);


-- ================================================
-- Section 2: Indexes
-- ================================================
CREATE INDEX idx_person_email ON Person(email);
CREATE INDEX idx_movie_title ON Movie(title);
CREATE INDEX idx_showtime_movie_id ON Showtime(movie_id);
CREATE INDEX idx_ticket_customer_id ON Ticket(customer_id);
CREATE INDEX idx_payment_method ON Payment(payment_method);

-- ================================================
-- Section 3: Stored Procedures
-- ================================================

-- 3.1 LoginUser Procedure
DELIMITER //
CREATE PROCEDURE LoginUser (
    IN p_email VARCHAR(100),
    IN p_password VARCHAR(255),
    OUT p_person_id INT,
    OUT p_user_type ENUM('admin', 'registered', 'regular'),
    OUT p_status VARCHAR(50)
)
BEGIN
    DECLARE v_password_hash VARCHAR(255);
    
    SELECT password_hash, user_type, person_id 
    INTO v_password_hash, p_user_type, p_person_id
    FROM Person 
    WHERE email = p_email;
    
    IF v_password_hash = SHA2(p_password, 256) THEN
        SET p_status = 'Login Successful';
    ELSE
        SET p_status = 'Invalid Credentials';
        SET p_person_id = NULL;
        SET p_user_type = NULL;
    END IF;
END;
//
DELIMITER ;

-- 3.2 MakeReservation Procedure
DELIMITER //
CREATE PROCEDURE MakeReservation (
    IN p_person_id INT,
    IN p_showtime_id INT,
    IN p_seat_row CHAR(1),
    IN p_seat_number INT,
    OUT p_status VARCHAR(50)
)
BEGIN
    DECLARE v_seat_id INT;
    DECLARE v_is_taken BOOLEAN;

    SELECT seat_id, taken INTO v_seat_id, v_is_taken
    FROM Seat 
    WHERE `row` = p_seat_row AND column_number = p_seat_number;

    IF v_seat_id IS NULL THEN
        SET p_status = 'Seat Not Found';
    ELSEIF v_is_taken THEN
        SET p_status = 'Seat Already Taken';
    ELSE
        INSERT INTO Ticket (seat_id, showtime_id, customer_id, status)
        VALUES (v_seat_id, p_showtime_id, p_person_id, 'active');
        
        UPDATE Seat SET taken = TRUE WHERE seat_id = v_seat_id;
        
        SET p_status = 'Reservation Successful';
    END IF;
END;
//
DELIMITER ;

-- Optionally insert the admin user here
INSERT INTO Person (name, email, password_hash, user_type, created_at, updated_at) 
VALUES ('Admin User', 'admin@movietheater.com', SHA2('adminpassword', 256), 'admin', NOW(), NOW());
