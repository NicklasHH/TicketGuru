# Create an AppUser

Create an AppUser.

**URL** : `localhost:8080/api/appusers/newAppUser`

**Method** : `POST`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide below required values.

```json
{
    "username": "String min=1, max=25",
    "password": "String min=1, max=50",
    "eventDate" : "String",
}
```

**Data example**

```json
{
{
    "appUserId": 1,
    "username": "Postman NEW one",
    "password": "Testing REST api"
}
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

