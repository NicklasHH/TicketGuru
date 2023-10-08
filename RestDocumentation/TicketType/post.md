# Create a TicketType

Create a tickettype.

**URL** : `localhost:8080/api/tickettypes`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
    "ticketType": "String min=1, max=50",
    "price": "Double min=1, max=10"
}
```

**Data example** `creating new tickettype`

```json
{
    "ticketType": "Opiskelija",
    "price": 10.0
}

```

## Success Response

**Code** : `200 OK`  

**Data example**:

```json
{
    "ticketType": "Opiskelija",
    "price": 10.0
}

```

**Content example** :
```json
{

    "ticketTypeId": 4,
    "ticketType": "Opiskelija",
    "price": 10.0
}
```

## Error response  

**Condition**: If the tickettype already exists.

**Code** : `409 Conflict`  

