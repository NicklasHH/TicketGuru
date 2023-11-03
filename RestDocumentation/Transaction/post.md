# Create a sales transaction

Create a new sales transaction.

**Endpoint** : `/api/transactions`

**Method** : `POST`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide below required values.

```json
{
    "amount": "double",
    "transactionOk": "boolean"

}
```

**Data example**

```json
{
    "amount": "123456.8",
    "transactionOk": "true"

}
```

## Success Response

**Code** : `200 OK`  

**Content example** :
```json
{
    "transactionId": 12,
    "amount": 123456.8,
    "transactionOk": true,
    "transactionDate": "2023-11-03",
    "transactionTime": "19:09:04"
}
```

## Error response  

**Code** : `400 Bad Request`  

**Data example**

```json
{
    "amount": "has to be number",
    "transactionOk" : "false"
    
}
```


**Message** : `JSON parse error: Cannot deserialize value of type `double` from String \"has to be number\": not a valid `double` value (as String to convert)`  





