# Edit an AppUser

Edit an Appuser.

**URL** : `http://localhost:8080/api/appusers/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
  "username": "String min=1, max=25",
  "password": "String min=1, max=50",
  "role": {
    "roleId": "int"
  }
}
```

**Data example**

```json
{
  "username": "uusitunnus123",
  "password": "salasana",
  "role": {
    "roleId": 1
  }
}
```

## Success Response

**Code** : `201 Created`

**Data example**

```json
{
  "username": "uusitunnus123",
  "password": "salasana",
  "role": {
    "roleId": 1,
    "roleName": "Admin"
  }
}
```

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "username": "admin",
  "password": "salasana"
}
```

**Message** :

```json
{
  "role": "must not be null"
}
```
