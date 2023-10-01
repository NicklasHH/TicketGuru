# Create a sales transaction

Create a new sales transaction.

**URL** : `http://localhost:8080/api/transactions`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : No

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
    "amount": 1500,
    "transactionDate" : "01.10.2023 new POSTMAN "
}
```

**Data example**

```json
{
    "transactionId": 6,
    "amount": 1500.0,
    "transactionDate": "01.10.2023 new POSTMAN "
}
```

## Success Response

**Code** : `200 OK`  

**Content example** :
```json
{
    "transactionId": 6,
    "amount": 1500.0,
    "transactionDate": "01.10.2023 new POSTMAN "
}
```

## Error response  

**Code** : `400 Bad Request`  
**Data example**

```json
{
    "amount": error,
    "transactionDate" : "01.10.2023 new POSTMAN ",
    
}
```


**Message** : `JSON parse error: Unrecognized token 'error': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')`

