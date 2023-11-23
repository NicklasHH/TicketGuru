# Show how many tickets left by eventId

Show count of tickets left with chosen eventId.

**Endpoint** : `/api/tickets/ticketsLeft/1`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If there event found with id

**Code** : `200 OK`

**Content example** : This example response contains number of tickets left.

```
195
```

## Error Response

**Condition** : If not found.

**Code** : `404 NOT FOUND`

**Content** : ` `
