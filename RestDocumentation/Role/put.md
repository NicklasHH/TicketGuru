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
        "roleName": "Adminii"
    }
```

## Success Response

**Code** : `200 OK`

## Error response  

**Code** : `400 Bad Request`  

**Message** : `JSON parse error`
