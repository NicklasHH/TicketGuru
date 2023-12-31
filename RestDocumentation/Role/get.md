# Show all Roles

Show information about all roles.

**Endpoint** : `/api/roles`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any roles

**Code** : `200 OK`

**Content example** : This example response contains details of all roles.

```json
[
  {
    "roleId": 1,
    "roleName": "Admin"
  },
  {
    "roleId": 2,
    "roleName": "Lipunmyyjä"
  },
  {
    "roleId": 3,
    "roleName": "Lipuntarkastaja"
  }
]
```

## Error Response

**Condition** : If there are no roles

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response provides information about all roles. In the case of no roles, an HTTP 404 NOT FOUND response is returned with an empty JSON object.

---

# Show single role

Show information about role based on its unique identifier `id`.

**Endpoint** : `/api/roles/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified role.

```json
{
  "roleId": 1,
  "roleName": "Admin"
}
```

## Error Response

**Condition** : If there is no role with given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single role based on its unique identifier. In the case of no role being found with the provided `id`, an HTTP 404 NOT FOUND response is returned with an empty JSON object.

---

# Show Specific role Details

**Possible endpoints** :  
`/api/roles/{id}/rolename`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified endpoint is valid.

**Code** : `200 OK`

**Content example from** : `/api/roles/1/rolename`

```json
{
  "rolename": "Admin"
}
```

## Error Response

**Condition** : If the specified endpoint is not found or there are no roles.

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint allows you to retrieve specific information about a single role based on the specified endpoint: username. If the specified endpoint is valid, an HTTP 200 OK response will be returned with relevant information. If the endpoint is not found or there are no roles, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
