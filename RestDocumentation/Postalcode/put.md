# Edit an Postalcode

Edit an Postalcode.

**Endpoint** : `/api/postalcodes/{postalcode}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : NO

**Authority required** : None

**Data constraints**

Provide postalcode and values to modify.

```json
{
  "postOffice": "String"
}
```

**Data example**

```json
{
  "postOffice": "vaihdettu"
}
```

---

## Success Response

**Code** : `200 OK`

**Data example**

```json
{
    "postOffice": "vaihdettu"
}
```

**Content example** :

```json
{
    "postalcode": "00100",
    "postOffice": "vaihdettu"
}
```

---

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "postOffice": ""
}
```

**Message** : `Postitoimipaikan on oltava vähintään 2 kirjainta`
