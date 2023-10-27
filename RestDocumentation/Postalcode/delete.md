# Delete single Postalcode

delete single postalcode based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/postalcode/{postcode}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the postalcode is found

**Code** : `200 OK`

## Error Response

**Condition** : If the postalcode is not found with the given `Postalcode String`

**Code** : `404 NOT FOUND`


