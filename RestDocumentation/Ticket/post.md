# Create an Ticket

Create an Ticket.

**Endpoint** : `/api/tickets`

**Method** : `POST`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide below required values.

```json
{
    "event": {"eventId": "eventId"},
    "ticketType": {"ticketTypeId": "ticketTypeId"},
    "transaction": {
        "transactionId": "transactionId",
        "amount" : "double", 
        "transactionOk" : "boolean"    
    },
    "isChecked": "boolean"
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
        "transactionId": 1,
        "amount" : 23, 
        "transactionOk" : true

    
    },
    "isChecked": true
}
```

## Success Response

**Code** : `200 OK`  

**Content example** :
```json
{
    "ticketId": 13,
    "event": {
        "eventId": 1,
        "eventName": null,
        "description": null,
        "eventDate": null,
        "eventTime": null,
        "ticketCount": 0,
        "venue": null
    },
    "ticketType": {
        "ticketTypeId": 1,
        "ticketType": null,
        "event": null,
        "price": 0.0
    },
    "transaction": {
        "transactionId": 1,
        "amount": 23.0,
        "transactionOk": true,
        "transactionDate": "2023-11-03",
        "transactionTime": "18:04:37"
    },
    "isChecked": true
}
```

## Error response  

Unexpected input:  
```json
{
    "event": {"eventId": 120023},
    "ticketType": {"ticketTypeId": 12345},
    "transaction": {"transactionId": 12345},
    "isChecked": null
}
```

**Code** : `400 Bad Request`  

**Message** :  

```json
{
    "event": "Event ID:tä ei löytynyt",
    "ticketType": "Ticket Type ID:tä ei löytynyt",
    "transaction": "Transaction ID:tä ei löytynyt",
    "isChecked": "True tai False vaihtoehdot"
    
}
```
