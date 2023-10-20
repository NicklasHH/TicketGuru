# Delete single Role

delete single role based on its unique identifier `id`.

**URL** : `http://localhost:8080/api/roles/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints** : `{}`

---

## Success Response

**Condition** : If the role is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the role that will be removed.

```json
{
  "roleId": 4,
  "roleName": "Lipuntarkastaja2"
}
```

---

## Error Response

**Condition** : If the role is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the role before its deletion. Please note that once you receive an HTTP 200 OK response, the role will be permanently deleted.
