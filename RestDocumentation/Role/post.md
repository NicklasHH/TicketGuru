# Create an Role

Create an AppURoleser.

**Endpoint** : `/api/roles`

**Method** : `POST`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide below required values.

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

**Content example** :

```json
{
  "roleName": "Admin"
}
```

**Message** : `Roolin nimi on jo käytössä.`
