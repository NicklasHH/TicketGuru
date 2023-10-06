# Delete single Venue

delete single venue based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/venues/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

---

## Success Response

**Condition** : If the venue is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the venue that will be removed.

```json
{
  "venueId": 1,
  "place": "Jäähalli",
  "streetAddress": "jäähallintie 1",
  "postalcode": {
    "postalcode": "00100",
    "postOffice": "Helsinki"
  }
}
```

---

## Error Response

**Condition** : If the venue is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the venue before its deletion. Please note that once you receive an HTTP 200 OK response, the venue will be permanently deleted.
