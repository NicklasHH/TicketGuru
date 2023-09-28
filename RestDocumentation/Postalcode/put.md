# Edit an Postalcode

Edit an Postalcode.

**URL** : `http://localhost:8080/api/postalcodes/{postalcode}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None 

**Data constraints**

Provide postalcode and values to modify.

```json
{
    "postalcode": "String"
    "postOffice": "String"
}
```

**Data example**

```json
{
    "postalcode": "00100",
    "postOffice": "vaihdettu"
}
```

## Success Response

**Code** : `200 OK`

## Error response  

**Code** : `400 Bad Request`  

**Message** : `JSON parse error`
