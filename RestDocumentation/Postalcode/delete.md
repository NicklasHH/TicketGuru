# Delete single Postalcode

delete single postalcode based on its unique identifier `id`.

**Endpoint** : `/api/postalcode/{postcode}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Authentication required** : NO

**Authority required** : None

**Data constraints** : `{}`

---

## Success Response

**Condition** : If the postalcode is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the postalcodes that will be removed.

```json
{
    "postalcode": "99999",
    "postOffice": "Korvatunturi"
}
```

---

## Error Response

**Condition** : If the postalcode is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

Note that once you receive an HTTP 200 OK response, the postalcode will be permanently deleted.


