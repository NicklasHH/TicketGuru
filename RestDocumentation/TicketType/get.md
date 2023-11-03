# Show all TicketTypes

Show information about all tickettypes.

**Endpoint** : `/api/tickettypes`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any tickettypes

**Code** : `200 OK`

**Content example** : This example response contains details of all tickettypes.

```json
[
    {
        "ticketTypeId": 1,
        "ticketType": "Lapsi",
        "price": 5.0
    },
    {
        "ticketTypeId": 2,
        "ticketType": "Eläkeläinen",
        "price": 15.0
    },
    {
        "ticketTypeId": 3,
        "ticketType": "Varusmies",
        "price": 10.0
    }
]
```

## Error Response

**Condition** : If there are no tickettypes

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response provides information about all tickettypes. In the case of no tickettypes, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___


# Show single ticketType

Show information about tickettype based on its unique identifier `id`.

**Endpoint** : `/api/tickettypes/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified tickettype.

```json
{
    {
        "ticketTypeId": 1,
        "ticketType": "Lapsi",
        "price": 5.0
    }
}
```

## Error Response

**Condition** : If there is no tickettype with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single tickettype based on its unique identifier. In the case of no tickettype being found with the provided `id`, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___

# Show Specific appuser Details

**Possible endpoints** :  
`/api/appusers/{id}/price`<br> 

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `http://localhost:8080/api/tickettypes/1/price` 

```json
{
    "price": 5.0
}
```

## Error Response

**Condition** :  If the specified endpoint is not found or there are no tickettypes.

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes
This endpoint allows you to retrieve specific information about a single tickettype based on the specified endpoint, such as price. If the specified endpoint is valid, an HTTP 200 OK response will be returned with relevant information. If the endpoint is not found or there are no tickettypes, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
