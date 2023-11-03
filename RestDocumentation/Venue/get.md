# Show all Venues

Show information about all Venues.

**Endpoint** : `/api/venues`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any venues

**Code** : `200 OK`

**Content example** : This example response contains details of all venues.

```json
[
  {
    "venueId": 1,
    "place": "Jäähalli",
    "streetAddress": "Jäähallintie 1",
    "postalcode": {
      "postalcode": "00100",
      "postOffice": "Helsinki"
    }
  },
  {
    "venueId": 2,
    "place": "Vesihalli",
    "streetAddress": "Vesihallintie 2",
    "postalcode": {
      "postalcode": "00200",
      "postOffice": "Espoo"
    }
  },
  {
    "venueId": 3,
    "place": "Aurinkohalli",
    "streetAddress": "Aurinkohallintie 3",
    "postalcode": {
      "postalcode": "99999",
      "postOffice": "Korvatunturi"
    }
  }
]
```

## Error Response

**Condition** : If there are no venues

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response provides information about all venues. In the case of no venues, an HTTP 404 NOT FOUND response is returned with an empty JSON object.

---

# Show single venue

Show information about venue based on its unique identifier `id`.

**Endpoint** : `/api/venues/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified venue.

```json
{
  "venueId": 1,
  "place": "Jäähalli",
  "streetAddress": "Jäähallintie 1",
  "postalcode": {
    "postalcode": "00100",
    "postOffice": "Helsinki"
  }
}
```

## Error Response

**Condition** : If there is no venue with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single venue based on its unique identifier. In the case of no venue being found with the provided `id`, an HTTP 404 NOT FOUND response is returned with an empty JSON object.

---

# Show Specific venue Details

**Possible endpoints** :  
`/api/venues/{id}/postalcode`  
`/api/venues/{id}/place`  
`/api/venues/{id}/streetaddress`<br>

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `/api/venues/1/place`

```json
{
  "Place": "Jäähalli"
}
```

## Error Response

**Condition** : If the specified endpoint is not found or there are no venues.

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint allows you to retrieve specific information about a single venue based on the specified endpoint, such as username, password or role. If the specified endpoint is valid, an HTTP 200 OK response will be returned with relevant information. If the endpoint is not found or there are no venues, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
