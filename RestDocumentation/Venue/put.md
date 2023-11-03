# Edit an Venue

Edit an venue.

**Endpoint** : `/api/venues/{id}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
  "place": "String min=1, max=150",
  "streetAddress": "String min=1, max=150",
  "postalcode": {
    "postalcode": "String min=5, max=5"
  }
}
```

**Data example**

```json
{
  "place": "Aurinkohalli",
  "streetAddress": "Muokattu tie",
  "postalcode": {
    "postalcode": "99999"
  }
}
```

---

## Success Response

**Code** : `201 Created`

**Data example**

```json
{
  "place": "Aurinkohalli",
  "streetAddress": "Uusi tie",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

**Content example**

```json
{
  "venueId": 3,
  "place": "Aurinkohalli",
  "streetAddress": "Uusi tie",
  "postalcode": {
    "postalcode": "00200",
    "postOffice": "Espoo"
  }
}
```

---

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "place": "Jäähalli",
  "streetAddress": "Uusi tie",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

**Message** : `Paikka nimellä Jäähalli on jo olemassa toisella id:llä.`

---

**Code** : `409 Conflict`

**Data example**

```json
{
  "place": "Aurinkohalli",
  "streetAddress": "Muokattu tie",
  "postalcode": {
    "postalcode": "9999"
  }
}
```

**Message** : `Paikan postinumeron on oltava 5 numeroa.`
