# Delete single Event

delete single event based on its unique identifier `id`.

**Endpoint** : `/api/events/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

---

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

Note that once you receive an HTTP 200 OK response, the event will be permanently deleted.
