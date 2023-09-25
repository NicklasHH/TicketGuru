# Show all Events

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
___


# Show single Event

Show information about single event based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/events/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified event.

```json
{
  "eventId": 1,
  "eventName": "Esimerkkitapahtuma",
  "description": "Esim",
  "eventDate": "12-12-2023",
  "eventTime": "18:00",
  "ticketCount": 100
}
```

## Error Response

**Condition** : If there is no event with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single event based on its unique identifier. In the case of no event being found with the provided `id`, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___

# Show Specific Event Details

**Possible endpoints** :  
`http://localhost:8080/api/events/{id}/eventName`  
`http://localhost:8080/api/events/{id}/eventDate`  
`http://localhost:8080/api/events/{id}/eventTime`  
`http://localhost:8080/api/events/{id}/ticketCount`  
`http://localhost:8080/api/events/{id}/description`  
`http://localhost:8080/api/events/{id}/venue`  

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `http://localhost:8080/api/events/{id}/venue` 

```json
{
    "venueId": 1,
    "place": "a",
    "streetAddress": "b"
}
```

## Error Response

**Condition** :  If the specified endpoint is not found or there are no events.

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes
This endpoint allows you to retrieve specific information about a single event based on the specified endpoint, such as event name, date, time, ticket count, description, or venue details. If the specified endpoint is valid, an HTTP 200 OK response will be returned with relevant information. If the endpoint is not found or there are no events, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
