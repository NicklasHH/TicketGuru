# Show all Appusers

Show information about all appusers.

**URL** : `http://localhost:8080/api/appusers`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any appusers

**Code** : `200 OK`

**Content example** : This example response contains details of all appusers.

```json
[
    {
        "appUserId": 1,
        "username": "admin",
        "password": "admin"
    },
    {
        "appUserId": 2,
        "username": "käyttis",
        "password": "salis"
    }
]
```

## Error Response

**Condition** : If there are no appusers

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response provides information about all appusers. In the case of no appusers, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___


# Show single appuser

Show information about appuser based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/appusers/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified appuser.

```json
{
    "appUserId": 1,
    "username": "admin",
    "password": "admin"
}
```

## Error Response

**Condition** : If there is no appuser with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single appuser based on its unique identifier. In the case of no appuser being found with the provided `id`, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___

# Show Specific appuser Details

**Possible endpoints** :  
`http://localhost:8080/api/appusers/{id}/username`  
`http://localhost:8080/api/appusers/{id}/password`  
`http://localhost:8080/api/appusers/{id}/roleId`<br> 

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `http://localhost:8080/api/appusers/1/username` 

```json
{
    "username": "admin"
}
```

## Error Response

**Condition** :  If the specified endpoint is not found or there are no appusers.

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes
This endpoint allows you to retrieve specific information about a single appuser based on the specified endpoint, such as username, password or role. If the specified endpoint is valid, an HTTP 200 OK response will be returned with relevant information. If the endpoint is not found or there are no appusers, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
