# Edit an Ticket

Edit an Ticket.

**Endpoint** : `/api/tickets/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide ID and values to modify.

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
    "transaction": {"transactionId": 2},
    "isChecked": true
}
```

## Success Response

**Code** : `200 OK`

```json
{
    "ticketId": 13,
    "event": {
        "eventId": 1,
        "eventName": "Tapahtuma 1",
        "description": "Lisätietoja",
        "eventDate": "2023-09-18",
        "eventTime": "12:00:00",
        "ticketCount": 100,
        "venue": {
            "venueId": 2,
            "place": "Vesihalli",
            "streetAddress": "Vesihallintie 2",
            "postalcode": {
                "postalcode": "00200",
                "postOffice": "Espoo"
            }
        }
    },
    "ticketType": {
        "ticketTypeId": 1,
        "ticketType": "Lapsi",
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
        "price": 5.0
    },
    "transaction": {
        "transactionId": 2,
        "amount": 25.0,
        "transactionOk": true,
        "transactionDate": "2023-10-10",
        "transactionTime": "10:10:10"
    },
    "isChecked": true
}
```

## Error response  

Id does not exist:  

**Code** : `404 Not Found`  

**Message** : `Tickettiä ei löytynyt id:llä 13000`  

Unexpected input:  
```json
{
    "event": {"eventId": 120023},
    "ticketType": {"ticketTypeId": 12345},
    "transaction": {"transactionId": 12345},
    "isChecked": null
}
```

**Code** : `409 Conflict`  

**Message** :  

```json
{
    "event": "Event ID:tä ei löytynyt",
    "ticketType": "Ticket Type ID:tä ei löytynyt",
    "transaction": "Transaction ID:tä ei löytynyt",
    "isChecked": "True tai False vaihtoehdot"
    
}
```
