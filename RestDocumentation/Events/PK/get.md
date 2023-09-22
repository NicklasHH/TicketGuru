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
