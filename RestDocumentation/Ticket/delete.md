# Delete single Ticket

delete single ticket based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/tickets/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

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

## Error Response

**Condition** : If the ticket is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`
