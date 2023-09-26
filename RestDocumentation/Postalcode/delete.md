# Delete single Postalcode

delete single postalcode based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/postalcode/{postcode}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the postalcode is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the postalcode that will be removed.

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

**Condition** : If the postalcode is not found with the given `Postalcode String`

**Code** : `404 NOT FOUND`

**Content** : `{}`

