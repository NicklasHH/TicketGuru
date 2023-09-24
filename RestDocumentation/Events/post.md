# Create an Event

Create an Event.

**URL** : `localhost:8080/api/events/newEvent`

**Method** : `POST`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide below required values.

```json
{
    "eventName": "String min=1, max=100",
    "description": "String min=1, max=500",
    "eventDate" : "String",
    "eventTime" : "String",
    "ticketCount" : "int"
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

