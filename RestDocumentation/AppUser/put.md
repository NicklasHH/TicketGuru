# Edit an AppUser

Edit an Appuser.

**Endpoint** : `/api/appusers/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

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
  "username": "vaihdettu tunnus",
  "password": "salasanaaa",
  "role": {
    "roleId": 3
  }
}
```

---

## Success Response

**Code** : `200 OK`

**Data example**

```json
{
  "username": "vaihdettu tunnus",
  "password": "salasanaaa",
  "role": {
    "roleId": 3
  }
}
```

**Content example** :

```json
{
  "appUserId": 3,
  "username": "vaihdettu tunnus",
  "password": "salasanaaa",
  "role": {
    "roleId": 3,
    "roleName": "Lipuntarkastaja"
  }
}
```

---

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "username": "toinen tunnus",
  "password": "salasana"
}
```

**Message** :

```json
{
  "role": "must not be null"
}
```

---

**Data example**

```json
{
  "username": "admin",
  "password": "salasanaaa",
  "role": {
    "roleId": 3
  }
}
```

**Message** : `Appuser nimelä Admin on jo olemassa toisella id:llä.`
