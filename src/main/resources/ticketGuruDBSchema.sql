SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS AppUsers;
DROP TABLE IF EXISTS Postalcodes;
DROP TABLE IF EXISTS Venues;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS TicketTypes;
DROP TABLE IF EXISTS Tickets;
SET FOREIGN_KEY_CHECKS=1;



-- Luo Roles-taulu
CREATE TABLE Roles (
  roleId BIGINT AUTO_INCREMENT PRIMARY KEY,
  roleName VARCHAR(255) NOT NULL
);
-- Lisätään tiedot
INSERT INTO Roles (roleName) 
VALUES ('Admin'),
		('Lipunmyyjä'),
		('Lipuntarkastaja');


-- Luo AppUsers-taulu
CREATE TABLE AppUsers (
  userId BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  roleId BIGINT NOT NULL,
  FOREIGN KEY (roleId) REFERENCES Roles(roleId)
);
-- Lisätään tiedot
INSERT INTO AppUsers (username, password, roleId) 
VALUES ('admin', 'salis1', 1),
		('käyttis', 'salis2', 2),
		('HessuHopo', 'salis3', 2);


-- Luo Postalcodes-taulu
CREATE TABLE Postalcodes (
  postalcode VARCHAR(255) PRIMARY KEY NOT NULL,
  postOffice VARCHAR(255) NOT NULL
);
-- Lisätään tiedot
INSERT INTO Postalcodes (postalcode, postOffice) 
VALUES ('00100', 'Helsinki'),
		('00200', 'Espoo'),
		('99999', 'Korvatunturi');


-- Luo Venues-taulu
CREATE TABLE Venues (
  venueId BIGINT AUTO_INCREMENT PRIMARY KEY,
  place VARCHAR(255) NOT NULL,
  streetAddress VARCHAR(255) NOT NULL,
  postalcode VARCHAR(255) NOT NULL,
  FOREIGN KEY (postalcode) REFERENCES Postalcodes(postalcode)
);
-- Lisätään tiedot
INSERT INTO Venues (place, streetAddress, postalcode)
VALUES ('Jäähalli', 'Tie a', '00100'),
		('Vesihalli', 'Tie b', '00200'),
		('Aurinkohalli', 'Tie c', '99999');


-- Luo Events-taulu
CREATE TABLE Events (
  eventId BIGINT AUTO_INCREMENT PRIMARY KEY,
  eventName VARCHAR(255) NOT NULL,
  eventDate DATE NOT NULL,
  eventTime TIME NOT NULL,
  ticketCount INT NOT NULL,
  venueId BIGINT NOT NULL,
  description TEXT,
  FOREIGN KEY (venueId) REFERENCES Venues(venueId)
);
-- Lisätään tiedot
INSERT INTO Events (eventName, eventDate, eventTime, ticketCount, venueId, description) 
VALUES ('eventin nimi1', '2023-09-18', '12:00', 100, 2, 'lisätietoja'),
		('eventin nimi 2', '2023-09-19', '15:00', 200, 1, 'lisätiedoton'),
		('eventin nimi 3', '2023-09-20', '18:00', 300, 3, NULL),
		('eventin nimi 4', '2023-09-20', '18:00', 300, 3, NULL),
		('eventin nimi 5', '2023-09-20', '18:00', 300, 3, NULL),
		('eventin nimi 6', '2023-09-20', '18:00', 300, 3, NULL);


-- Luo TicketTypes-taulu
CREATE TABLE TicketTypes (
  ticketTypeId BIGINT AUTO_INCREMENT PRIMARY KEY,
  TicketType VARCHAR(255) NOT NULL,
  eventId BIGINT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (eventId) REFERENCES Events(eventId)
);
-- Lisätään tiedot
INSERT INTO TicketTypes (TicketType, eventId, price)
VALUES ('Lapsi', 2, '5'),
		('Eläkeläinen', 2, '15'),
		('Varusmies', 2, '20'),
		('Normaali', 2, '50');


-- Luo Transactions-taulu
CREATE TABLE Transactions (
  transactionId BIGINT AUTO_INCREMENT PRIMARY KEY,
  Amount DECIMAL(10, 2) NOT NULL,
  TransactionDate DATE NOT NULL
);
-- Lisätään tiedot
INSERT INTO Transactions (Amount, TransactionDate) 
VALUES (15, '2023-09-20'),
		(25, '2023-09-21'),
		(35, '2023-09-22'),
		(35, '2023-09-22'),
		(35, '2023-09-22');


-- Luo Tickets-taulu
CREATE TABLE Tickets (
  ticketId BIGINT AUTO_INCREMENT PRIMARY KEY,
  ticketTypeId BIGINT NOT NULL,
  eventId BIGINT NOT NULL,
  transactionId BIGINT NOT NULL,
  isChecked BOOLEAN,
  FOREIGN KEY (ticketTypeId) REFERENCES TicketTypes(ticketTypeId),
  FOREIGN KEY (eventId) REFERENCES Events(eventId),
  FOREIGN KEY (transactionId) REFERENCES Transactions(transactionId)
);
-- Lisätään tiedot
INSERT INTO Tickets (ticketTypeId, eventId, transactionId, isChecked) 
VALUES (1, 1, 1, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 0),
		(2, 2, 2, 1);


SELECT * FROM  Roles;
SELECT * FROM  AppUsers;
SELECT * FROM  Postalcodes;
SELECT * FROM  Venues;
SELECT * FROM  Transactions;
SELECT * FROM  Events;
SELECT * FROM  TicketTypes;
SELECT * FROM  Tickets;