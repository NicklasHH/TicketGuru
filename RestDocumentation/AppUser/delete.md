# Delete single Appuser

delete single appuser based on its unique identifier `id`.

**Endpoint** : `/api/appusers/{id}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : Role:Admin

**Data constraints** : `{}`

---

## Success Response

**Condition** : If the appuser is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the appuser that will be removed.

```json
{
  "appUserId": 4,
  "username": "uusitunnus123",
  "password": "salasana",
  "role": {
    "roleId": 1,
    "roleName": "Admin"
  }
}
```

---

## Error Response

**Condition** : If the appuser is not found with the given `id`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the appuser before its deletion. Please note that once you receive an HTTP 200 OK response, the appuser will be permanently deleted.
