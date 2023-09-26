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
    "roleName": "Lipuntarkastaja"
}

```

## Success Response

**Code** : `200 OK`  

**Content example** :
```json
{
    "roleId": 3,
    "roleName": "Lipuntarkastaja"
}
```

## Error response  

**Code** : `400 Bad Request`  

**Message** : `JSON parse error`

