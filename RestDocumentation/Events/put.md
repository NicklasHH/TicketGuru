# Edit an Event

Edit an Event.

**Endpoint** : `/api/appusers/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
  "eventID": "long id",
  "eventName": "String min=1, max=100",
  "description": "String min=1, max=500",
  "eventDate": "String",
  "eventTime": "String",
  "ticketCount": "int"
}
```

**Data example**

```json
{
  "eventId": 1,
  "eventName": "Postman edited event id 1",
  "description": "Testing RestApi, EDIT endpoint",
  "eventDate": "01012023",
  "eventTime": "21:30",
  "ticketCount": 23
}
``` 
---

## Success Response

**Code** : `200 OK`

**Data example**

```json
{
    "eventName": "Postman edited event id 1",
    "description": "Testing RestApi, EDIT endpoint",
    "eventDate": "2023-10-27",
    "eventTime": "21:30",
    "ticketCount": 23,
        "venue": {
        "venueId": 2
        }
}
```

**Content example** :

```json
{
    "eventId": 2,
    "eventName": "Postman edited event id 1",
    "description": "Testing RestApi, EDIT endpoint",
    "eventDate": "2023-10-27",
    "eventTime": "21:30",
    "ticketCount": 23,
    "venue": {
        "venueId": 2,
        "place": "Vesihalli",
        "streetAddress": "vesihallintie 2",
        "postalcode": {
            "postalcode": "00200",
            "postOffice": "Espoo"
        }
    }
}
```
---

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
    "eventName": "Postman edited event id 1",
    "description": "Testing RestApi, EDIT endpoint",
    "eventDate": "2023-10-27",
    "eventTime": "21:30",
    "ticketCount": 23,
        "venue": {
        "venueId": 5
        }
}
```

**Message** :`Venue ID:tä ei löytynyt`