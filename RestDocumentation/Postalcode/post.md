# Create an Postalcode

Create an Postalcode.

**Endpoint** : `/api/postalcodes`

**Method** : `POST`

**Content-Type** : `application/json`

**Authentication required** : NO

**Authority required** : None

**Data constraints**

Provide below required values.

```json
{
    "postalcode": "String min=5, max=5",
    "postOffice": "String"
}
```

**Data example**

```json
{
    "postalcode": "12345",
    "postOffice": "esim"
}

```

## Success Response

**Code** : `201 Created`  

## Error Response

**Condition** : If the postalcode already exist

**Message** : `Postinumero 00162 on jo olemassa.`
