# TicketGuru REST API

Documentation of TicketGuru REST API.

### Base-URL

`http://localhost:8080/`  
`http://ticketguru-ohjelmistoprojekti.rahtiapp.fi/`

### Endpoints

##### Endpoint listing

Each endpoint manipulates or displays information:

[**AppUser**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/AppUser)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/get.md#show-all-appusers): `GET /api/appusers`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/get.md#show-single-appuser): `GET /api/appusers/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/get.md#show-specific-appuser-details): `GET /api/appusers/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/post.md): `POST /api/appusers`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/put.md): `PUT /api/appusers/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/delete.md): `DELETE /api/appusers/{id}`
___
[**Events**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/Events)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/get.md#show-all-events): `GET /api/events`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/get.md#show-single-event): `GET /api/events/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/get.md#show-specific-event-details): `GET /api/events/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/post.md): `POST /api/events`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/put.md): `PUT /api/events/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/delete.md): `DELETE /api/events/{id}`
___
[**Postalcode**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/Postalcode)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/get.md#show-all-postalcodes): `GET /api/postalcodes`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/get.md#show-single-postalcode): `GET /api/postalcodes/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/get.md): `GET /api/postalcodes/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/post.md): `POST /api/postalcodes`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/put.md): `PUT /api/postalcodes/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/delete.md): `DELETE /api/postalcodes/{id}`
___
[**Role**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/Role)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/get.md#show-all-roles): `GET /api/roles`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/get.md#show-single-role): `GET /api/roles/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/get.md#show-specific-role-details): `GET /api/roles/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/post.md): `POST /api/roles`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/put.md): `PUT /api/roles/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/delete.md): `DELETE /api/roles/{id}`
___
[**Ticket**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/Ticket)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/get.md): `GET /api/tickets`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/get.md): `GET /api/tickets/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/get.md): `GET /api/tickets/{id}/*`
* [Show single ticket info in QR code](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/get.md#show-single-ticket-info-in-qr-code): `GET /api//tickets/qr/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/post.md): `POST /api/tickets`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/put.md): `PUT /api/tickets/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/delete.md): `DELETE /api/tickets/{id}`
* [Show how many tickets left by eventId](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/ticketsLeftByEventId.md): `GET /api/tickets/ticketsLeft/{id}`
* [Show tickets by eventId](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/getAllByEventId.md): `GET /api/tickets/eventSales/{id}`
___
[**TicketType**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/TicketType)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/get.md): `GET /api/tickettypes`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/get.md): `GET /api/tickettypes/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/get.md): `GET /api/tickettypes/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/post.md): `POST /api/tickettypes`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/put.md): `PUT /api/tickettypes/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/delete.md): `DELETE /api/tickettypes/{id}`
___
[**Transaction**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/Transaction)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/get.md): `GET /api/transactions`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/get.md): `GET /api/transactions/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/get.md): `GET /api/transactions/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/post.md): `POST /api/transactions`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/put.md): `PUT /api/transactions/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/put.md): `DELETE /api/transactions/{id}`
___
[**Venue**](https://github.com/NicklasHH/TicketGuru/tree/master/RestDocumentation/Venue)
* [Show all](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/get.md#show-all-venues): `GET /api/venues`
* [Show single](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/get.md#show-single-venue): `GET /api/venues/{id}`
* [Show details](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/get.md#show-specific-venue-details): `GET /api/venues/{id}/*`
* [New](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/post.md): `POST /api/venues`
* [Edit](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/put.md): `PUT /api/venues/{id}`
* [Delete](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/delete.md): `DELETE /api/venues/{id}`
___
