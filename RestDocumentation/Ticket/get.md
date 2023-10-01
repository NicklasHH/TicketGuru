# Show all Tickets

Show information about all tickets.

**URL** : `http://localhost:8080/api/tickets`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

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
    },
    {
        "ticketId": 2,
        "event": {
            "eventId": 2,
            "eventName": "eventin nimi 2",
            "description": "lisätiedot",
            "eventDate": "19.9.2029",
            "eventTime": "15.00",
            "ticketCount": 200,
            "venue": {
                "venueId": 2,
                "place": "c",
                "streetAddress": "d",
                "postalcode": {
                    "postalcode": "00200",
                    "postOffice": "Espoo"
                }
            }
        },
        "ticketType": {
            "ticketTypeId": 2,
            "ticketType": "Eläkeläinen",
            "price": 15.0
        },
        "transaction": {
            "transactionId": 2,
            "amount": 25.0,
            "transactionDate": "21.9.2023"
        },
        "isChecked": null
    }
]
```

## Error Response

**Condition** : If there are no tickets

**Code** : `404 NOT FOUND`

**Content** : `{}`

# Show single ticket

Show information about ticket based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/tickets/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified ticket.

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

**Condition** : If there is no ticket with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

# Show Specific ticket Details

**Possible endpoints** :  
`http://localhost:8080/api/tickets/{id}/ticketType`  
`http://localhost:8080/api/tickets/{id}/event`  
`http://localhost:8080/api/tickets/{id}/transaction`
`http://localhost:8080/api/tickets/{id}/isChecked`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `http://localhost:8080/api/tickets/1/ticketType` 

```json
{
    "ticketTypeId": 1,
    "ticketType": "Lapsi",
    "price": 5.0
}
```

## Error Response

**Condition** :  If the specified endpoint is not found or there are no tickets.

**Code** : `404 NOT FOUND`

**Content** : `{}`
