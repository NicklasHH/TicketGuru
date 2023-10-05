# Edit an Venue

Edit an venue.

**URL** : `http://localhost:8080/api/venues/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
  "place": "String min=1, max=150",
  "streetAddress": "String min=1, max=150",
  "postalcode": {
    "postalcode": "String min=5, max=5"
  }
}
```

**Data example**

```json
{
  "place": "Paikka",
  "streetAddress": "Testing RestApi, adding new event, round1",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

## Success Response

**Code** : `201 Created`

**Content example** : This example response contains the details of the edited venue.

```json
{
  "venueId": 4,
  "place": "Paikka",
  "streetAddress": "Testing RestApi, adding new event, round1",
  "postalcode": {
    "postalcode": "00200",
    "postOffice": "Espoo"
  }
}
```

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "place": "Paikka",
  "streetAddress": "Testing RestApi, adding new event, round1",
  "postalcode": {
    "postalcode": "0020"
  }
}
```

**Message** : `Paikan postinumeron on oltava 5 numeroa`
