# Show all postalcodes

Show information about all postalcodes.

**URL** : `http://localhost:8080/api/postalcodes`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any postalcode

**Code** : `200 OK`

**Content example** : This example response contains details of all postalcodes.

```json
[
    {
        "postalcode": "00100",
        "postOffice": "Helsinki"
    },
    {
        "postalcode": "00200",
        "postOffice": "Espoo"
    }
]
```

## Error Response

**Condition** : If there are no postalcode

**Code** : `404 NOT FOUND`

**Content** : `{}`

# Show single postalcode

Show information about postalcode based on its unique identifier `string`.

**URL** : `http://localhost:8080/api/postalcodes/{postalcode}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `id` is found

**Code** : `200 OK`

**Content example** : This example response contains details of the identified postalcode.

```json
{
    "postalcode": "00100",
    "postOffice": "Helsinki"
}
```

## Error Response

**Condition** : If there is no postalcode with given `string`

**Code** : `404 NOT FOUND`
