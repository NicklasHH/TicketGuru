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
  password VARCHAR(100) NOT NULL,
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
  ticket_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
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