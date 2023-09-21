# Edit an Event

Edit an Event.

**URL** : `http://localhost:8080/api/events/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide ID and values to modify.

```json
{
    "eventID : long id,
    "eventName": "String min=1, max=100",
    "description": "String min=1, max=500",
    "eventDate" : "String",
    "eventTime" : "String",
    "ticketCount" : int
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
