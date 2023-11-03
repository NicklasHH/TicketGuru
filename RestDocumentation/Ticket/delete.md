# Delete single Ticket

delete single ticket based on its unique identifier `id`.

**Endpoint** : `/api/tickets/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the ticket is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the ticket that will be removed.
```json
{
    "ticketId": 1,
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

## Error Response

**Condition** : If the ticket is not found with the given `id`

**Code** : `404 NOT FOUND`

**Message** : `Tickettiä ei löytynyt id:llä 8.`  
