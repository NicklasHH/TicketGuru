# Create an Ticket

Create an Ticket.

**URL** : `http://localhost:8080/api/tickets`

**Method** : `POST`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide below required values.

```json
{
    "event": {
        "eventId": "eventId"
    },
    "ticketType": {
        "ticketTypeId": "ticketTypeId"
    },
    "transaction": {
        "transactionId": "transactionId"
    },
    "isChecked": "Boolean"
}
```

**Data example**

```json
{
    "event": {
        "eventId": 1
    },
    "ticketType": {
        "ticketTypeId": 1
    },
    "transaction": {
        "transactionId": 1
    },
    "isChecked": true
}
```

## Success Response

**Code** : `200 OK`  

**Content example** :
```json
{
    "ticketId": 4,
    "event": {
        "eventId": 1,
        "eventName": "eventin nimi 1",
        "description": "lisätiedot",
        "eventDate": "18.9.2029",
        "eventTime": "12.00",
        "ticketCount": 100,
        "venue": {
            "venueId": 1,
            "place": "a",
            "streetAddress": "b",
            "postalcode": {
                "postalcode": "00100",
                "postOffice": "Helsinki"
            }
        }
    },
    "ticketType": {
        "ticketTypeId": 1,
        "ticketType": "Lapsi",
        "price": 5.0
    },
    "transaction": {
        "transactionId": 1,
        "amount": 15.0,
        "transactionDate": "20.9.2023"
    },
    "isChecked": null
}
```

## Error response  

**Code** : `400 Bad Request`  

**Message** : `JSON parse error`
