DROP TABLE IF EXISTS App_users;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Ticket_types;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS Venues;
DROP TABLE IF EXISTS Postalcodes;
 

CREATE TABLE Roles (
  role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(255) NOT NULL
);


CREATE TABLE App_users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_id BIGINT,
  FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);


CREATE TABLE Postalcodes (
  postalcode VARCHAR(255) PRIMARY KEY NOT NULL,
  post_office VARCHAR(255) NOT NULL
);


CREATE TABLE Venues (
  venue_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  place VARCHAR(255) NOT NULL,
  street_address VARCHAR(255) NOT NULL,
  postalcode VARCHAR(255),
  FOREIGN KEY (postalcode) REFERENCES Postalcodes(postalcode)
);


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

CREATE TABLE Ticket_types (
  ticket_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ticket_type VARCHAR(255) NOT NULL,
  event_id BIGINT ,
  price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Transactions (
  transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL(10, 2) NOT NULL,
  transaction_ok BOOLEAN,
  transaction_date VARCHAR(10) NOT NULL,
  transaction_time VARCHAR(8) NOT NULL
);

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

INSERT INTO Roles (role_name) VALUES ('Admin');
INSERT INTO Roles (role_name) VALUES ('Lipunmyyjä');
INSERT INTO Roles (role_name) VALUES ('Lipuntarkastaja');
INSERT INTO Roles (role_name) VALUES ('Testirooli');
INSERT INTO Roles (role_name) VALUES ('Asiakas');

INSERT INTO App_Users(username, password, role_Id) VALUES ('admin', '$2a$10$A/WjQTrfD8k7bH648/IzZuvRye1NmMHt4AJ.nmzZKY.sXo6u2QnSS', 1);
INSERT INTO App_Users(username, password, role_Id) VALUES ('lipunmyyjä', '$2a$10$tpkvJ649LdZnHKyfOG.P.eH8sLCmZd5diUXRhhlEXL02bvG8xzogy', 2);
INSERT INTO App_Users(username, password, role_Id) VALUES ('lipuntarkastaja', '$2a$10$uvLB4RZLN.tQMxwWXjUZMOajujEuTdYcqWNUGsd4EycZ/Onhz1AY6', 3);
INSERT INTO App_Users(username, password, role_Id) VALUES ('testi', '$2a$10$.Uhl.dZRcoYx1cmcBg6GJ.jx2aEliCF0t5IQKRM.nmdRMU2UDpbku', 4);


INSERT INTO Postalcodes (postalcode, post_Office) VALUES ('00100', 'Helsinki');
INSERT INTO Postalcodes (postalcode, post_Office) VALUES ('00200', 'Espoo');
INSERT INTO Postalcodes (postalcode, post_Office) VALUES ('99999', 'Korvatunturi');

INSERT INTO Venues(place, street_address, postalcode) VALUES ('Jäähalli','jäähallintie 1', '00100');
INSERT INTO Venues(place, street_address, postalcode) VALUES ('Vesihalli','vesihallintie 2', '00200');
INSERT INTO Venues(place, street_address, postalcode) VALUES ('Aurinkohalli','Aurinkohallintie 3', '99999');

INSERT INTO Events(event_name, event_Date, event_Time, ticket_count, venue_id, description) VALUES ('Tapahtuma 1', '2023-09-18', '12:00', 100, 2, 'Lisätietoja');
INSERT INTO Events(event_name, event_Date, event_Time, ticket_count, venue_id, description) VALUES ('Tapahtuma 2', '2023-09-19', '15:00', 200, 1, 'Lisätiedoton');
INSERT INTO Events(event_name, event_Date, event_Time, ticket_count, venue_id, description) VALUES ('Tapahtuma 3', '2023-09-20', '18:00', 300, 3, 'Ei voi puhua');
INSERT INTO Events(event_name, event_Date, event_Time, ticket_count, venue_id, description) VALUES ('Tapahtuma 4', '2023-09-21', '19:00', 400, 3, NULL);
INSERT INTO Events(event_name, event_Date, event_Time, ticket_count, venue_id, description) VALUES ('Tapahtuma 5', '2023-09-22', '20:00', 500, 3, NULL);
INSERT INTO Events(event_name, event_Date, event_Time, ticket_count, venue_id, description) VALUES ('Tapahtuma 6', '2023-09-23', '23:40', 600, 3, NULL);


INSERT INTO Ticket_types(Ticket_type, event_id, price) VALUES ('Lapsi', 1, '5');
INSERT INTO Ticket_types(Ticket_type, event_id, price) VALUES ('Eläkeläinen', 2,'15');
INSERT INTO Ticket_types(Ticket_type, event_id, price) VALUES ('Varusmies', 2,'10');
INSERT INTO Ticket_types(Ticket_type, event_id, price) VALUES ('Normaali', 2, '50');


INSERT INTO Transactions(Amount, transaction_ok, Transaction_Date, transaction_time) VALUES (15, 'true' , '2023-10-01', '10:35');
INSERT INTO Transactions(Amount, transaction_ok, Transaction_Date, transaction_time) VALUES (25, 'false', '2023-10-04', '21:40');
INSERT INTO Transactions(Amount, transaction_ok, Transaction_Date, transaction_time) VALUES (25, 'true', '2023-10-04', '12:34');
INSERT INTO Transactions(Amount, transaction_ok, Transaction_Date, transaction_time) VALUES (15, 'true' , '2023-10-01', '10:35');
INSERT INTO Transactions(Amount, transaction_ok, Transaction_Date, transaction_time) VALUES (25, 'false', '2023-10-04', '21:40');
INSERT INTO Transactions(Amount, transaction_ok, Transaction_Date, transaction_time) VALUES (25, 'true', '2023-10-04', '12:34');


INSERT INTO Tickets(Ticket_Type_Id, Event_id, transaction_Id, is_Checked) VALUES (1, 1, 1, 0);
INSERT INTO Tickets(Ticket_Type_Id, Event_id, transaction_Id, is_Checked) VALUES (2, 2, 2, 0);
INSERT INTO Tickets(Ticket_Type_Id, Event_id, transaction_Id, is_Checked) VALUES (2, 2, 2, 0);
INSERT INTO Tickets(Ticket_Type_Id, Event_id, transaction_Id, is_Checked) VALUES (2, 2, 2, 0);
INSERT INTO Tickets(Ticket_Type_Id, Event_id, transaction_Id, is_Checked) VALUES (2, 2, 2, 0);
INSERT INTO Tickets(Ticket_Type_Id, Event_id, transaction_Id, is_Checked) VALUES (2, 2, 2, 1);


SELECT * FROM Roles;
SELECT * FROM App_users;
SELECT * FROM Postalcodes;
SELECT * FROM Venues;
SELECT * FROM Transactions;
SELECT * FROM Events;
SELECT * FROM Ticket_types;
SELECT * FROM Tickets;