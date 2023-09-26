# Show all roles

Show information about all roles.

**URL** : `http://localhost:8080/api/roles`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any role

**Code** : `200 OK`

**Content example** : This example response contains details of all role.

```json
[
    {
        "roleId": 1,
        "roleName": "Admin"
    },
    {
        "roleId": 2,
        "roleName": "Lipunmyyjä"
    }
]
```

## Error Response

**Condition** : If there are no role

**Code** : `404 NOT FOUND`

**Content** : `{}`

# Show single role

Show information about role based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/roles/{id}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

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
