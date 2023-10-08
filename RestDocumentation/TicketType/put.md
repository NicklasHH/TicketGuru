# Edit a TicketType

Edit a tickettype.

**URL** : `http://localhost:8080/api/tickettypes/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None 

**Data constraints**

Provide ID and values to modify.

```json
{
    "ticketType": "String min=1, max=50",
    "price": "Double min=1, max=10"
}
```

**Data example**

```json
{
    "ticketType": "Edited Lapsi",
    "price": 6.0
}
```

## Success Response

**Code** : `201 Created`

**Data example**:

```json
{
    "ticketType": "Edited Lapsi",
    "price": 6.0
}
```

**Content example**:

```json
{
    "ticketTypeId": 1,
    "ticketType": "Edited Lapsi",
    "price": 6.0
}
```

## Error response  

**Code** : `404 Not Found`  

**Message** : `The requested resource could not be found.`
