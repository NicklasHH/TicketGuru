SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS App_users;
DROP TABLE IF EXISTS Postalcodes;
DROP TABLE IF EXISTS Venues;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS Ticket_types;
DROP TABLE IF EXISTS Tickets;

DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS app_users;
DROP TABLE IF EXISTS postalcodes;
DROP TABLE IF EXISTS venues;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS ticket_types;
DROP TABLE IF EXISTS tickets;
SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE roles (
  role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(50) NOT NULL
);

CREATE TABLE app_users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(25) NOT NULL,
  password VARCHAR(50) NOT NULL,
  role_id BIGINT,
  FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE postalcodes (
  postalcode VARCHAR(5) PRIMARY KEY NOT NULL,
  post_office VARCHAR(150) NOT NULL
);

CREATE TABLE venues (
  venue_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  place VARCHAR(150) NOT NULL,
  street_address VARCHAR(150) NOT NULL,
  postalcode VARCHAR(5),
  FOREIGN KEY (postalcode) REFERENCES postalcodes(postalcode)
);

CREATE TABLE events (
  event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_name VARCHAR(100) NOT NULL,
  event_date DATE NOT NULL,
  event_time TIME NOT NULL,
  ticket_count INT NOT NULL,
  venue_id BIGINT,
  description TEXT,
  FOREIGN KEY (venue_id) REFERENCES venues(venue_id)
);

CREATE TABLE ticket_types (
  ticket_type_id VARCHAR AUTO_INCREMENT PRIMARY KEY,
  ticket_type VARCHAR(50) NOT NULL,
  event_id BIGINT ,
  price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (event_id) REFERENCES events(event_id)
);

CREATE TABLE transactions (
  transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL(10, 2) NOT NULL,
  transaction_ok BOOLEAN,
  transaction_date VARCHAR(10) NOT NULL,
  transaction_time VARCHAR(8) NOT NULL
);

CREATE TABLE tickets (
  ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ticket_type_id BIGINT,
  event_id BIGINT ,
  transaction_id BIGINT,
  is_checked BOOLEAN,
  FOREIGN KEY (ticket_type_id) REFERENCES ticket_types(ticket_type_id),
  FOREIGN KEY (event_id) REFERENCES events(event_id),
  FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id)
);


INSERT INTO roles (role_name) 
VALUES ('Admin'),
		('Lipunmyyjä'),
		('Lipuntarkastaja'),
		('Testirooli'),
		('Asiakas');

INSERT INTO app_users (username, password, role_id) 
VALUES ('admin', '$2a$10$A/WjQTrfD8k7bH648/IzZuvRye1NmMHt4AJ.nmzZKY.sXo6u2QnSS', 1), -- PW:admin
		('lipunmyyjä', '$2a$10$tpkvJ649LdZnHKyfOG.P.eH8sLCmZd5diUXRhhlEXL02bvG8xzogy', 2),-- PW:lipunmyyjä
		('lipuntarkastaja', '$2a$10$uvLB4RZLN.tQMxwWXjUZMOajujEuTdYcqWNUGsd4EycZ/Onhz1AY6', 3),-- PW:lipuntarkastaja
		('testi', '$2a$10$.Uhl.dZRcoYx1cmcBg6GJ.jx2aEliCF0t5IQKRM.nmdRMU2UDpbku', 4);-- PW:testi

INSERT INTO postalcodes (postalcode, post_office) 
VALUES ('00100', 'Helsinki'),
		('00200', 'Espoo'),
		('99999', 'Korvatunturi');

INSERT INTO venues (place, street_address, postalcode)
VALUES ('Jäähalli', 'Jäähallintie 1', '00100'),
		('Vesihalli', 'Vesihallintie 2', '00200'),
		('Aurinkohalli', 'Aurinkohallintie 3', '99999');

INSERT INTO events (event_name, event_date, event_time, ticket_count, venue_id, description) 
VALUES ('Tapahtuma 1', '2023-09-18', '12:00', 100, 2, 'Lisätietoja'),
		('Tapahtuma 2', '2023-09-19', '15:00', 200, 1, 'Lisätiedoton'),
		('Tapahtuma 3', '2023-09-20', '18:00', 300, 3, 'Ei voi puhua'),
		('Tapahtuma 4', '2023-09-21', '19:00', 400, 3, NULL),
		('Tapahtuma 5', '2023-09-22', '20:00', 500, 3, NULL),
		('Tapahtuma 6', '2023-09-23', '23:40', 600, 3, NULL);

INSERT INTO ticket_types (ticket_type, event_id, price)
VALUES ('Lapsi', 2, '5'),
		('Eläkeläinen', 2, '15'),
		('Varusmies', 2, '20'),
		('Normaali', 2, '50');

INSERT INTO transactions (amount, transaction_ok, transaction_date, transaction_time) 
VALUES (15, 0, "2023-10-10", "10:10:10"),
		(25, 1, "2023-10-10", "10:10:10"),
		(35, 0, "2023-10-10", "10:10:10"),
		(35, 1, "2023-10-10", "10:10:10"),
		(35, 0, "2023-10-10", "10:10:10"),
		(35, 1, "2023-10-10", "10:10:10");

INSERT INTO tickets (ticket_type_id, event_id, transaction_id, is_checked) 
VALUES (1, 1, 1, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 1);
