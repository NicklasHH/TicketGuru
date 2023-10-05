# Create an Role

Create an AppURoleser.

**URL** : `http://localhost:8080/api/roles`

**Method** : `POST`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide below required values.

```json
{
    "roleName": "String min=1, max=50",
}
```

**Data example**

```json
{
    "roleName": "Lipuntarkastaja2"
}

```

## Success Response

**Code** : `201 Created`  

**Content example** :
```json
{
    "roleName": "Lipuntarkastaja2"
}
```

## Error response  

**Code** : `409 Conflict` 

**Content example** :
```json
{
    "roleName": "Lipuntarkastaja2"
}
```

**Message** : `Roolin nimi on jo käytössä.`

