# Edit a TicketType

Edit a tickettype.

**Endpoint** : `/api/tickettypes/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "ticketType": "String min=1, max=50",
    "event": {"eventId" : "long id"},
    "price": "Double min=1, max=10",  
}
```

**Data example** `edit existing tickettype`

```json
{
    "ticketType": "Postman muokattu",
    "event": {"eventId": 2},
    "price": 10.0
}
```

## Success Response

**Code** : `201 Created`

**Data example**:

```json
{
    "ticketType": "Edited Lapsi",
    "price": 6.0
}
```

**Content example**:

```json
{
    "ticketTypeId": 1,
    "ticketType": "Postman muokattu",
    "event": {
        "eventId": 2,
        "eventName": "Tapahtuma 2",
        "description": "Lisätiedoton",
        "eventDate": "2023-09-19",
        "eventTime": "15:00:00",
        "ticketCount": 200,
        "venue": {
            "venueId": 1,
            "place": "Jäähalli",
            "streetAddress": "Jäähallintie 1",
            "postalcode": {
                "postalcode": "00100",
                "postOffice": "Helsinki"
            }
        }
    },
    "price": 10.0
}
```

## Error response  

**Code** : `404 Not Found`  

**Message** : `The requested resource could not be found.`
