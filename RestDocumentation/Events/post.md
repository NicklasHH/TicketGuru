# Create an Event

Create an Event.

**URL** : `localhost:8080/api/events`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints**

Provide below required values.

```json
{
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
  "eventName": "Postman",
  "description": "Testing RestApi",
  "eventDate": "02022022",
  "eventTime": "21:30",
  "ticketCount": 23
}
```

## Success Response

**Code** : `200 OK`

**Content example** :

```json
{
  "eventId": 6,
  "eventName": "Postman NEW one, adding new event2",
  "description": "Testing RestApi, POST endpoint",
  "eventDate": "02022022",
  "eventTime": "21:30",
  "ticketCount": 23
}
```

## Error response

**Code** : `400 Bad Request`

**Message** : `JSON parse error`
