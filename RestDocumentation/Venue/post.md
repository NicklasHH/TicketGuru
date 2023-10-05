# Create an Venue

Create an Venue.

**URL** : `http://localhost:8080/api/venues`

**Method** : `POST`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide below required values.

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
  "place": "Postman",
  "streetAddress": "Testing RestApi, adding new event, round1",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

## Success Response

**Code** : `201 Created`

**Content example** :

```json
{
  "venueId": 4,
  "place": "Postman",
  "streetAddress": "Testing RestApi, adding new event, round1",
  "postalcode": {
    "postalcode": "00200",
    "postOffice": null
  }
}
```

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "venueId": 4,
  "place": "Postman",
  "streetAddress": "Testing RestApi, adding new event, round1",
  "postalcode": {
    "postalcode": "00200",
    "postOffice": null
  }
}
```

**Message** : `Paikan nimi on jo käytössä.`
