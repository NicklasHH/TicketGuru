# Set Ticket as Used

Update the ticket's <strong> isChecked </strong>boolean to true.

**Endpoint** : `/api/tickets/{ticketId}/check`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

## Success Response

**Code** : `200 OK`

```json
{
    "ticketId": 2,
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
    "ticketType": {
        "ticketTypeId": 2,
        "ticketType": "Eläkeläinen",
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
        "price": 15.0
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

**Code** : `404 Not Found`  
**Code** : `405 Method Not Allowed` 
