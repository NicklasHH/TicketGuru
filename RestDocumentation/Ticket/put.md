# Edit an Ticket

Edit an Ticket.

**URL** : `http://localhost:8080/api/tickets/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None 

**Data constraints**

Provide ID and values to modify.

```json
{
    "ticketId": "long id",
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
    "ticketId": 1,
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

## Error response  

**Code** : `400 Bad Request`  

**Message** : `JSON parse error`
