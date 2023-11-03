# Create a TicketType

Create a tickettype.

**Endpoint** : `/api/tickettypes`

**Method** : `POST`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide below required values.

```json
{
    "ticketType": "String min=1, max=50",
    "event": {"eventId" : "long id"},
    "price": "Double min=1, max=10",
    
    
}
```

**Data example** `creating new tickettype`

```json
{
    "ticketType": "Postman",
    "event": {"eventId": 2},
    "price": 10.0
}

```

## Success Response

**Code** : `200 OK`  

**Data example**:

```json
{
    "ticketType": "Postman opiskelee",
    "event": {"eventId": 2},
    "price": 10.0
}
```

**Content example** :
```json
{
    "ticketTypeId": 10,
    "ticketType": "Postman opiskelee",
    "event": {
        "eventId": 2,
        "eventName": null,
        "description": null,
        "eventDate": null,
        "eventTime": null,
        "ticketCount": 0,
        "venue": null
    },
    "price": 10.0
}
```

## Error response  


**Code** : `409 Conflict`  

**Content example** : `In this example eventId is not found`

```json
{
    "ticketType": "Postman opiskelee",
    "event": {"eventId": 223},
    "price": 10.0
}
```

**Message** : `Event ID:tä ei löytynyt`
