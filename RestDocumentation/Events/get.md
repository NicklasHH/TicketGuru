# SHOW all Events

Show information about all events.

**URL** : `http://localhost:8080/api/events`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any events

**Code** : `200 OK`

**Content example** : This example response contains details of all events.

```json
[
  {
    "eventId": 1,
    "eventName": "Esimerkkitapahtuma",
    "description": "Esim",
    "eventDate": "12-12-2023",
    "eventTime": "18:00",
    "ticketCount": 100
  },
  {
    "eventId": 2,
    "eventName": "Postman",
    "description": "Testing RestApi, adding new event, round1",
    "eventDate": "02022022",
    "eventTime": "21:30",
    "ticketCount": 23
  }
]
```

## Error Response

**Condition** : If there are no events

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response provides information about all events. In the case of no events, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
