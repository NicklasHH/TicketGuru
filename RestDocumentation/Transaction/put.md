# Edit sales Transaction

Edit sales transaction.

**URL** : `http://localhost:8080/api/transactions/{transactionId}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None 

**Data constraints**

Provide ID and values to modify.

```json
{
    "amount": 1500,
    "transactionDate" : "edited POSTMAN 01102023"
}
```

**Data example**

```json
{
    "transactionId": 6,
    "amount": 1500.0,
    "transactionDate": "edited POSTMAN 01102023"
}
```

## Success Response

**Code** : `200 OK`

## Error response  

**Code** : `404 Not Found`  

**Message** : `The requested resource could not be found.`
