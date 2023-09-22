# DELETE single Event

delete single event based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/events/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the event is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the event that will be removed.

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

**Condition** : If the event is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the event before its deletion. Please note that once you receive an HTTP 200 OK response, the event will be permanently deleted.
