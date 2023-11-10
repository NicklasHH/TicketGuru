# Show all Tickets

Show information about all tickets.

**Endpoint** : `/api/tickets`

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
                        "postOffice": "Helsinki"
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
    },
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
        "isChecked": false
    }
]
```

## Error Response

**Condition** : If there are no tickets

**Code** : `404 NOT FOUND`

**Content** : `{}`

# Show single ticket

Show information about ticket based on its unique identifier `id`.

**Endpoint** : `/api/tickets/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None 

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
`/api/tickets/{id}/ticketType`<br> 
`/api/tickets/{id}/event`<br> 
`/api/tickets/{id}/transaction`<br> 
`/api/tickets/{id}/isChecked`<br> 

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None 

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `/api/tickets/1/ticketType` 

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

-------------------------------------------------------------------------------------
# Show single ticket info in QR code

Show information about ticket based on its unique identifier `id` in QR code

**Endpoint** : `/api/tickets/qr/{id}`

**Method** : `GET`

**Content-Type** : `image/png`

**Authentication required** : YES

**Authority required** : None  

**Data constraints** : `image`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified ticket.

```
text inside of QR code: 
Ticket_id
isChecked
Event_id
```

## Error Response

**Condition** : If there is no ticket with given `id`

**Code** : `200`

**Content example** : This example response contains details of the qr code.

```
text inside of QR code:
Ei lippua
```

