# Delete single TicketType

Delete single tickettype based on its unique identifier `id`.

**Endpoint** : `/api/tickettypes/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the tickettype is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the tickettype that will be removed.

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

## Error Response

**Condition** : If the tickettype is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the tickettype that has been deleted. Please note that once you have received an HTTP 200 OK response, the tickettype is permanently deleted.
