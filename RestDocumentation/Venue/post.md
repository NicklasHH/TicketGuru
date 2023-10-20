# Create an Venue

Create an Venue.

**URL** : `http://localhost:8080/api/venues`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide below required values.

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
  "place": "Postman",
  "streetAddress": "Testing RestApi, adding new venue",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

---

## Success Response

**Code** : `201 Created`

**Data example**

```json
{
  "place": "Ilmahalli",
  "streetAddress": "Ilmailutie 23",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

**Content example** :

```json
{
  "venueId": 4,
  "place": "Ilmahalli",
  "streetAddress": "Ilmailutie 23",
  "postalcode": {
    "postalcode": "00200",
    "postOffice": null
  }
}
```

---

## Error response

**Code** : `409 Conflict`

**Data example**

```json
{
  "place": "Vesihalli",
  "streetAddress": "Vesihallintie 23",
  "postalcode": {
    "postalcode": "00200"
  }
}
```

**Message** : `Paikka nimelä Vesihalli on jo olemassa toisella id:llä.`
