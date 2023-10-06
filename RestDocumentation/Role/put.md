# Edit an Role

Edit an Appuser.

**URL** : `http://localhost:8080/api/roles/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
  "roleName": "String min=1, max=50"
}
```

**Data example**

```json
{
  "roleName": "Lipuntarkastaja2"
}
```

---

## Success Response

**Code** : `201 Created`

**Data example**

```json
{
  "roleName": "Lipuntarkastaja2"
}
```

**Content example** :

```json
{
  "roleId": 4,
  "roleName": "Lipuntarkastaja2"
}
```

---

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "roleName": "Admin"
}
```

**Message** :`Roolin nimi on jo käytössä.`
