# Delete single TicketType

Delete single tickettype based on its unique identifier `id`.

**Endpoint** : `/api/tickettypes/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the tickettype is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the tickettype that will be removed.

```json
{
    "ticketTypeId": 1,
    "ticketType": "Lapsi",
    "price": 5.0
}
```

## Error Response

**Condition** : If the tickettype is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the tickettype before its deletion. Please note that once you receive an HTTP 200 OK response, the tickettype will be permanently deleted.