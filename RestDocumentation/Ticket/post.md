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
    "transaction": {"transactionId": "transactionId"},
    "isChecked": "Boolean"
}
```

**Data example**

```json
{
    "event": {"eventId": 1},
    "ticketType": {"ticketTypeId": 1},
    "transaction": {"transactionId": 1},
    "isChecked": true
}
```

## Success Response

**Code** : `200 OK`  

**Content example** :
```json
{
        "ticketId": 1,
        "event": {
            "eventId": 1,
            "eventName": "eventin nimi 1",
            "description": "lisätiedot",
            "eventDate": "18.9.2029",
            "eventTime": "12.00",
            "ticketCount": 100,
            "venue": {
                "venueId": 1,
                "place": "Jäähalli",
                "streetAddress": "jäähallintie 1",
                "postalcode": {
                    "postalcode": "00100",
                    "postOffice": "Helsinki"
                }
            }
        },
        "ticketType": {
            "ticketTypeId": 1,
            "ticketType": "Edited Lapsi",
            "price": 6.0
        },
        "transaction": {
            "transactionId": 1,
            "amount": 1500.0,
            "transactionOk": true,
            "transactionDate": "2023-10-09",
            "transactionTime": "03:25:29",
            "timestamp": "Last time edited: 2023-10-09 03:25:29"
        },
        "isChecked": true
    }
```

## Error response  

Unexpected input:  
```json
{
    "event": {"eventId": 1},
    "ticketType": {"ticketTypeId": 1},
    "transaction": null,
    "isChecked": null
}
```

**Code** : `400 Bad Request`  

**Message** :  

```json
{
    "isChecked": "isChecked cannot be null, true or false expected",
    "transaction": "must not be null"
}
```
