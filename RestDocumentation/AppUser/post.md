# Create an AppUser

Create an AppUser.

**URL** : `http://localhost:8080/api/appusers`

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
  "role": {
    "roleId": "roleId"
  }
}
```

**Data example**

```json
{
  "username": "uusitunnus1",
  "password": "Testing",
  "role": {
    "roleId": 1
  }
}
```

## Success Response

**Code** : `201 Created`

**Content example** :

```json
{
  "appUserId": 4,
  "username": "uusitunnus",
  "password": "Testing",
  "role": {
    "roleId": 1,
    "roleName": null
  }
}
```

## Error response

**Code** : `409 Conflict`

**Data example** :

```json
{
  "username": "Admin",
  "password": "Testing",
  "role": {
    "roleId": 1
  }
}
```

**Message** : `Käyttäjätunnus on jo käytössä.`
