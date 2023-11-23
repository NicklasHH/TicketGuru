# Show tickets by eventId

Show information about all tickets with chosen eventId.

**Endpoint** : `/api/tickets/eventSales/1`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any tickets

**Code** : `200 OK`

**Content example** : This example response contains details of all tickets.

```json
[
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
            "postOffice": "11111"
          }
        }
      },
      "price": 5.0
    },
    "transaction": {
      "transactionId": 1,
      "amount": 15.0,
      "transactionOk": false,
      "transactionDate": "2023-10-10",
      "transactionTime": "10:10:10"
    },
    "isChecked": false
  }
]
```

## Error Response

**Condition** : If there are no tickets

**Code** : `404 NOT FOUND`

**Content** : `{}`
