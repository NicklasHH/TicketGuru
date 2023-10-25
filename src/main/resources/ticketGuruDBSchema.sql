SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS App_users;
DROP TABLE IF EXISTS Postalcodes;
DROP TABLE IF EXISTS Venues;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS Ticket_types;
DROP TABLE IF EXISTS Tickets;
SET FOREIGN_KEY_CHECKS=1;


-- Luo Roles-taulu
CREATE TABLE Roles (
  role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(255) NOT NULL
);

-- Luo AppUsers-taulu
CREATE TABLE App_users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_id BIGINT,
  FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

-- Luo Postalcodes-taulu
CREATE TABLE Postalcodes (
  postalcode VARCHAR(255) PRIMARY KEY NOT NULL,
  post_office VARCHAR(255) NOT NULL
);

-- Luo Venues-taulu
CREATE TABLE Venues (
  venue_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  place VARCHAR(255) NOT NULL,
  street_address VARCHAR(255) NOT NULL,
  postalcode VARCHAR(255),
  FOREIGN KEY (postalcode) REFERENCES Postalcodes(postalcode)
);

-- Luo Events-taulu
CREATE TABLE Events (
  event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_name VARCHAR(255) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  ticket_count INT NOT NULL,
  venue_id BIGINT,
  description TEXT,
  FOREIGN KEY (venue_id) REFERENCES Venues(venue_id)
);

-- Luo TicketTypes-taulu
CREATE TABLE Ticket_types (
  ticket_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ticket_type VARCHAR(255) NOT NULL,
  event_id BIGINT ,
  price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

-- Luo Transactions-taulu
CREATE TABLE Transactions (
  transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL(10, 2) NOT NULL,
  transaction_ok BOOLEAN,
  transaction_date VARCHAR(10) NOT NULL,
  transaction_time VARCHAR(8) NOT NULL
);

-- Luo Tickets-taulu
CREATE TABLE Tickets (
  ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ticket_type_id BIGINT,
  event_id BIGINT ,
  transaction_id BIGINT,
  is_checked BOOLEAN,
  FOREIGN KEY (ticket_type_id) REFERENCES Ticket_types(ticket_type_id),
  FOREIGN KEY (event_id) REFERENCES Events(event_id),
  FOREIGN KEY (transaction_id) REFERENCES Transactions(transaction_id)
);


-- Lisätään tiedot -> Roles
INSERT INTO Roles (role_name) 
VALUES ('Admin'),
		('Työntekijä'),
		('Testirooli'),
		('Asiakas');

-- Lisätään tiedot -> App_users
INSERT INTO App_users (username, password, role_id) 
VALUES ('admin', '$2a$10$A/WjQTrfD8k7bH648/IzZuvRye1NmMHt4AJ.nmzZKY.sXo6u2QnSS', 1), -- PW:admin
		('lipunmyyjä', '$2a$10$tpkvJ649LdZnHKyfOG.P.eH8sLCmZd5diUXRhhlEXL02bvG8xzogy', 2),-- PW:lipunmyyjä
		('lipuntarkastaja', '$2a$10$uvLB4RZLN.tQMxwWXjUZMOajujEuTdYcqWNUGsd4EycZ/Onhz1AY6', 2),-- PW:lipuntarkastaja
		('testi', '$2a$10$.Uhl.dZRcoYx1cmcBg6GJ.jx2aEliCF0t5IQKRM.nmdRMU2UDpbku', 3);-- PW:testi

-- Lisätään tiedot -> Postalcodes
INSERT INTO Postalcodes (postalcode, post_office) 
VALUES ('00100', 'Helsinki'),
		('00200', 'Espoo'),
		('99999', 'Korvatunturi');

-- Lisätään tiedot -> Venues
INSERT INTO Venues (place, street_address, postalcode)
VALUES ('Jäähalli', 'Jäähallintie 1', '00100'),
		('Vesihalli', 'Vesihallintie 2', '00200'),
		('Aurinkohalli', 'Aurinkohallintie 3', '99999');

-- Lisätään tiedot -> Events
INSERT INTO Events (event_name, event_date, event_time, ticket_count, venue_id, description) 
VALUES ('Tapahtuma 1', '2023-09-18', '12:00', 100, 2, 'Lisätietoja'),
		('Tapahtuma 2', '2023-09-19', '15:00', 200, 1, 'Lisätiedoton'),
		('Tapahtuma 3', '2023-09-20', '18:00', 300, 3, 'Ei voi puhua'),
		('Tapahtuma 4', '2023-09-21', '19:00', 400, 3, NULL),
		('Tapahtuma 5', '2023-09-22', '20:00', 500, 3, NULL),
		('Tapahtuma 6', '2023-09-23', '23:40', 600, 3, NULL);

-- Lisätään tiedot -> Ticket_types
INSERT INTO Ticket_types (ticket_type, event_id, price)
VALUES ('Lapsi', 2, '5'),
		('Eläkeläinen', 2, '15'),
		('Varusmies', 2, '20'),
		('Normaali', 2, '50');

-- Lisätään tiedot -> transactions
INSERT INTO Transactions (amount, transaction_ok, transaction_date, transaction_time) 
VALUES (15, 0, "2023-10-10", "10:10:10"),
		(25, 1, "2023-10-10", "10:10:10"),
		(35, 0, "2023-10-10", "10:10:10"),
		(35, 1, "2023-10-10", "10:10:10"),
		(35, 0, "2023-10-10", "10:10:10"),
		(35, 1, "2023-10-10", "10:10:10");

-- Lisätään tiedot -> Tickets
INSERT INTO Tickets (ticket_type_id, event_id, transaction_id, is_checked) 
VALUES (1, 1, 1, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 1);

		

-- SELECT * FROM  Roles;
-- SELECT * FROM  App_users;
-- SELECT * FROM  Postalcodes;
-- SELECT * FROM  Venues;
-- SELECT * FROM  Transactions;
-- SELECT * FROM  Events;
-- SELECT * FROM  Ticket_types;
-- SELECT * FROM  Tickets;