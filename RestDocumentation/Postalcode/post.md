# Create an Postalcode

Create an Postalcode.

**URL** : `localhost:8080/api/events/postalcodes`

**Method** : `POST`

**Content-Type** : `application/json`

<!--**Auth required** : Yes

**Permissions required** : None -->

**Data constraints**

Provide below required values.

```json
{
    "postalcode": "String"
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
