# TicketGuru REST API

Documentation of TicketGuru REST API.

### Base-URL

`http://localhost:8080/`

### Endpoints

##### Endpoint listing

Each endpoint manipulates or displays information:

* [AppUser](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/get.md): `GET /api/appusers`
* [AppUser](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/get.md): `GET /api/appusers/{id}`
* [AppUser](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/post.md): `POST /api/appusers/newAppuser`
* [AppUser](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/put.md): `PUT /api/appusers/{id}`
* [AppUser](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/AppUser/delete.md): `DELETE /api/appusers/{id}`
* [Events](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/get.md#show-all-events): `GET /api/events`
* [Events](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/get.md#show-single-event): `GET /api/events/{id}`
* [Events](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/get.md#show-specific-event-details): `GET /api/events/{id}/*`
* [Events](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/post.md): `POST /api/events/newEvent`
* [Events](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/put.md): `PUT /api/events/{id}`
* [Events](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Events/delete.md): `DELETE /api/events/{id}`
* [Postalcode](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/get.md): `GET /api/postalcodes`
* [Postalcode](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/get.md): `GET /api/postalcodes`
* [Postalcode](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/post.md): `POST /api/postalcodes/{id}`
* [Postalcode](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/put.md): `PUT /api/postalcodes`
* [Postalcode](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Postalcode/delete.md): `DELETE /api/postalcodes/{id}`
* [Role](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/get.md): `GET /api/roles`
* [Role](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/get.md): `GET /api/roles/{id}`
* [Role](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/post.md): `POST /api/roles/newRole`
* [Role](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/put.md): `PUT /api/roles/{id}`
* [Role](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Role/delete.md): `DELETE /api/roles/{id}`
* [Ticket](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/get.md): `GET /api/tickets`
* [Ticket](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/get.md): `GET /api/tickets/{id}`
* [Ticket](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/post.md): `POST /api/tickets/newTicket`
* [Ticket](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/put.md): `PUT /api/tickets/{id}`
* [Ticket](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Ticket/delete.md): `DELETE /api/tickets/{id}`
* [TicketType](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/get.md): `GET /api/tickettypes`
* [TicketType](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/get.md): `GET /api/tickettypes/{id}`
* [TicketType](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/post.md): `POST /api/tickettypes/newTicketType`
* [TicketType](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/put.md): `PUT /api/tickettypes/{id}`
* [TicketType](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/delete.md): `DELETE /api/tickettypes/{id}`
* [Transaction](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/get.md): `GET /api/transactions`
* [Transaction](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/get.md): `GET /api/transactions/{id}`
* [Transaction](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/post.md): `POST /api/transactions/newTransaction`
* [Transaction](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/TicketType/put.md): `PUT /api/transactions/{id}`
* [Transaction](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Transaction/put.md): `DELETE /api/transactions/{id}`
* [Venue](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/get.md): `GET /api/venues`
* [Venue](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/get.md): `GET /api/venues/{id}`
* [Venue](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/post.md): `POST /api/venues/newVenue`
* [Venue](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/put.md): `PUT /api/venues/{id}`
* [Venue](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/Venue/delete.md): `DELETE /api/venues/{id}`
