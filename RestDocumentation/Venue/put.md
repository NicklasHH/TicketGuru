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
    "venueId": "long id",
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
    "eventId": 1,
    "place": "Postman",
    "streetAddress": "Testing RestApi, adding new event, round1",
    "postalcode": {
        "postalcode": "00200"
        }
}
```

## Success Response

**Code** : `200 OK`

**Content example** : This example response contains the details of the edited venue.
```json
{
    "venueId": 1,
    "place": "Postman",
    "streetAddress": "Testing RestApi, adding new event, round1",
    "postalcode": {
        "postalcode": "00200",
        "postOffice": "Espoo"
    }
}
```

## Error response  

**Code** : `400 Bad Request`  

**Message** : `JSON parse error`
