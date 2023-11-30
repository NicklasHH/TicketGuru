INSERT INTO roles (role_name) 
VALUES ('Admin'),
		('Lipunmyyja'),
		('Lipuntarkastaja'),
		('Testirooli'),
		('Asiakas');

INSERT INTO app_users (username, password, role_id) 
VALUES ('admin', '$2a$10$A/WjQTrfD8k7bH648/IzZuvRye1NmMHt4AJ.nmzZKY.sXo6u2QnSS', 1), -- PW:admin
		('lipunmyyja', '$2a$10$tpkvJ649LdZnHKyfOG.P.eH8sLCmZd5diUXRhhlEXL02bvG8xzogy', 2),-- PW:lipunmyyja
		('lipuntarkastaja', '$2a$10$uvLB4RZLN.tQMxwWXjUZMOajujEuTdYcqWNUGsd4EycZ/Onhz1AY6', 3),-- PW:lipuntarkastaja
		('testi', '$2a$10$.Uhl.dZRcoYx1cmcBg6GJ.jx2aEliCF0t5IQKRM.nmdRMU2UDpbku', 4);-- PW:testi

INSERT INTO postalcodes (postalcode, post_office) 
VALUES ('00100', 'Helsinki'),
		('00200', 'Espoo'),
		('99999', 'Korvatunturi');

INSERT INTO venues (place, street_address, postalcode)
VALUES ('Jaahalli', 'Jaahallintie 1', '00100'),
		('Vesihalli', 'Vesihallintie 2', '00200'),
		('Aurinkohalli', 'Aurinkohallintie 3', '99999');

INSERT INTO events (event_name, event_date, event_time, ticket_count, venue_id, description) 
VALUES ('Tapahtuma 1', '2023-09-18', '12:00', 100, 2, 'Lisatietoja'),
		('Tapahtuma 2', '2023-09-19', '15:00', 200, 1, 'Lisatiedoton'),
		('Tapahtuma 3', '2023-09-20', '18:00', 300, 3, 'Ei voi puhua'),
		('Tapahtuma 4', '2023-09-21', '19:00', 400, 3, NULL),
		('Tapahtuma 5', '2023-09-22', '20:00', 500, 3, NULL),
		('Tapahtuma 6', '2023-09-23', '23:40', 600, 3, NULL);

INSERT INTO ticket_types (ticket_type, event_id, price)
VALUES ('Lapsi', 2, '5'),
		('Elakelainen', 2, '15'),
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
