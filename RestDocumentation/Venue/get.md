# Show all Venues

Show information about all Venues.

**URL** : `http://localhost:8080/api/venues`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

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
        "streetAddress": "jäähallintie 1",
        "postalcode": {
            "postalcode": "00100",
            "postOffice": "Helsinki"
        }
    },
    {
        "venueId": 2,
        "place": "Vesihalli",
        "streetAddress": "vesihallintie 2",
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
___


# Show single venue

Show information about venue based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/venues/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified venue.

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

## Error Response

**Condition** : If there is no venue with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single venue based on its unique identifier. In the case of no venue being found with the provided `id`, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___

# Show Specific appuser Details

**Possible endpoints** :  
`http://localhost:8080/api/venues/{id}/postalcode`  
`http://localhost:8080/api/venues/{id}/place`  
`http://localhost:8080/api/venues/{id}/streetaddress`<br> 

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `http://localhost:8080/api/venues/1/place` 

```json
{
    "Place": "Jäähalli"
}
```

## Error Response

**Condition** :  If the specified endpoint is not found or there are no appusers.

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes
This endpoint allows you to retrieve specific information about a single appuser based on the specified endpoint, such as username, password or role. If the specified endpoint is valid, an HTTP 200 OK response will be returned with relevant information. If the endpoint is not found or there are no appusers, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
